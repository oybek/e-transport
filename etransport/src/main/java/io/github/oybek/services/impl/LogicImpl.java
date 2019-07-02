package io.github.oybek.services.impl;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec2f;
import io.github.oybek.entities.Stop;
import io.github.oybek.lib.vk.*;
import io.github.oybek.lib.vk.groups.MessagesApi;
import io.github.oybek.lib.vk.groups.SendReq;
import io.github.oybek.services.Logic;
import io.github.oybek.services.StopService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LogicImpl implements Logic {

    Logger logger = LoggerFactory.getLogger(LogicImpl.class);

    private MessagesApi messagesApi;
    private StopService stopService;

    @Value("${vk.messages.url}")
    private String messagesUrl;

    @Value("${vk.access-token}")
    private String accessToken;

    @Value("${vk.version}")
    private String version;

    private Map<Integer, String> lastStopName = new HashMap<>();

    private final String busIcon = "🚌";
    private final String tramIcon = "🚋";
    private final String trollIcon = "🚎";

    public LogicImpl(StopService stopService) {
        this.stopService = stopService;
    }

    @PostConstruct
    public void init() {
        messagesApi = new io.github.oybek.lib.vk.groups.MessagesApiUnirestImpl(messagesUrl);
    }

    @Override
    public void onMessage(io.github.oybek.lib.vk.Message message) {
        String text = "";

        if (message.getText() != null && message.getText().length() > 0) {
            text = message.getText().substring(0, Math.min(message.getText().length(), 40));
            switch (message.getText()) {
                case   busIcon: text = lastStopName.getOrDefault(message.getFromId(), "") +    "автобусы"; break;
                case  tramIcon: text = lastStopName.getOrDefault(message.getFromId(), "") +     "трамваи"; break;
                case trollIcon: text = lastStopName.getOrDefault(message.getFromId(), "") + "троллейбусы"; break;
            }

            String stopName = text
                    .replaceAll("автобусы?", "")
                    .replaceAll("трамва(и|й)", "")
                    .replaceAll("тролл?ейбусы?", "")
                    .trim();

            lastStopName.put(message.getFromId(), stopName);

            if (text.matches(".*автобусы?.*")) {
                List<Stop> stop = stopService.findBusStopsByName(stopName);
                text = stop.size() == 0
                        ? "На остановке '" + stopName + "' автобусы не ходят"
                        : genReachesText(stop);
            } else
            if (text.matches(".*трамва(и|й).*")) {
                List<Stop> stop = stopService.findTramStopsByName(stopName);
                text = stop.size() == 0
                        ? "На остановке '" + stopName + "' трамваи не ходят"
                        : genReachesText(stop);
            } else
            if (text.matches(".*тролл?ейбусы?.*")) {
                List<Stop> stop = stopService.findTrollStopsByName(stopName);
                text = stop.size() == 0
                        ? "На остановке '" + stopName + "' троллейбусы не ходят"
                        : genReachesText(stop);
            } else {
                if (stopService.isTramStop(stopName)) text = genReachesText(stopService.findTramStopsByName(stopName));
                else if (stopService.isTrollStop(stopName)) text = genReachesText(stopService.findTrollStopsByName(stopName));
                else if (stopService.isBusStop(stopName)) text = genReachesText(stopService.findBusStopsByName(stopName));
                else {
                    text = "Напишите название остановки, например\n'Дом кино'";
                }
            }
            new Thread(() -> stopService.findBusStopsByName(stopName)).start();
        } else if (message.getGeo() != null) {
            Stop busStop = stopService.findNearestBusStops(
                    new Vec2d(message.getGeo().getCoordinates().getLatitude(), message.getGeo().getCoordinates().getLongitude())
            );
            Stop tramStop = stopService.findNearestTramStops(
                    new Vec2d(message.getGeo().getCoordinates().getLatitude(), message.getGeo().getCoordinates().getLongitude())
            );
            Stop trollStop = stopService.findNearestTrollStops(
                    new Vec2d(message.getGeo().getCoordinates().getLatitude(), message.getGeo().getCoordinates().getLongitude())
            );
            text = "Ближайшие остановки:\n"
                + busIcon   + busStop.getNames().get(0) + "\n"
                + tramIcon  + tramStop.getNames().get(0) + "\n"
                + trollIcon + trollStop.getNames().get(0);
        } else {
            text = "Напишите название остановки, например\n'Дом кино'";
        }

        SendReq sendReq
                = new SendReq()
                .setUserId(message.getFromId())
                .setMessage(text)
                .setV(version)
                .setAccessToken(accessToken)
                .setKeyboard(genKeyboard("", "", ""));

        try {
            logger.info(sendReq.toString());
            messagesApi.send(sendReq);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
    }

    public String genReachesText(List<Stop> stops) {
        return stops.stream().map(stop -> {
            return stopTypeToEmoji(stop.getType())
                    + stop.getNames().get(0) + " на " + stop.getDirection() + "\n"
                    + stop.getReaches().stream().map(reach ->
                        reach.getTime() <= 0
                            ? reach.getRoute() + "-й " + stopTypeToRus(stop.getType()) + " уже подъезжает"
                            : reach.getRoute() + "-й " + stopTypeToRus(stop.getType()) + " через " + reach.getTime() + " мин."
                    ).collect(Collectors.joining("\n"));
        }).collect(Collectors.joining("\n\n"));
    }

    public String stopTypeToEmoji(String type) {
        switch (type) {
            case "bus": return "🚌";
            case "tram": return "🚋";
            case "troll": return "🚎";
            default: return "";
        }
    }

    public String stopTypeToRus(String type) {
        switch (type) {
            case "bus": return "автобус";
            case "tram": return "трамвай";
            case "troll": return "троллейбус";
            default: return "";
        }
    }

    private Keyboard genKeyboard(String bus, String tram, String troll) {
        return new io.github.oybek.lib.vk.Keyboard()
            .setOneTime(false)
            .setButtons(asList(
                asList(new Button().setAction(new Location())),
                asList(
                    new io.github.oybek.lib.vk.Button().setAction(new Text().setLabel(busIcon + bus)),
                    new Button().setAction(new Text().setLabel("🚎" + tram)),
                    new Button().setAction(new Text().setLabel("🚋" + troll)))));
    }

    public static <T> List<T> asList(T ... items) {
        List<T> list = new ArrayList<T>();
        for (T item : items) {
            list.add(item);
        }

        return list;
    }
}
