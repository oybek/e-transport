package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.Entities.StopInfo;

import java.util.List;
import java.util.stream.Collectors;

public class Bot {
    private enum State {
          HELP
        , TRAM
        , TROLL }

    private Ettu ettu;
    private State state = State.TRAM;
    private Message lastMessage = null;

    //
    Bot(Ettu ettu) {
        this.ettu = ettu;
    }

    //
    public Message getReaction(Message msg) {
        switch (state) {
            default: {
                break;
            }

            case HELP: {
                return new Message()
                        .setUid(msg.getUid())
                        .setText("Для того чтобы я мог найти ближайшую остановку, отправьте мне свои координаты, вот как это делается:")
                        .setAttachment("doc-163915852_464149858");
            }

            case TROLL: {
                // if user agree to see info about trolls...
                if (msg.hasText() && msg.getText().toLowerCase().matches("да|lf|\\+")) {

                    // ... get info about troll stop
                    List<StopInfo> stopInfos = ettu.getNearestTrollStops(lastMessage.getGeo(), 2);

                    // if no trolleybus found ...
                    if (stopInfos == null) {
                        state = State.HELP;
                        return getReaction(msg);
                    } else {
                        state = State.TRAM;
                        return new Message()
                                .setUid(msg.getUid())
                                .setText(
                                    stopInfos
                                        .stream()
                                        .map( stopInfo -> "🚎 Остановка: " + stopInfo.getTextInfo() )
                                        .collect(Collectors.joining("\n")));
                    }
                } else {
                    // ... react to message as new request
                    state = State.TRAM;
                    return getReaction(msg);
                }
            }

            case TRAM: {
                // no geolocation provided
                if (!msg.hasGeo()) {
                    return new Message()
                            .setUid(msg.getUid())
                            .setText("Для того чтобы я мог найти ближайшую остановку, отправьте мне свои координаты, вот как это делается:")
                            .setAttachment("doc-163915852_464149858");
                }

                List<StopInfo> stopInfos = ettu.getNearestTramStops(msg.getGeo(), 2);

                if (stopInfos == null) {
                    state = State.HELP;
                    return getReaction(msg);
                } else {
                    state = State.TROLL;
                    lastMessage = msg;
                    return new Message()
                            .setUid(msg.getUid())
                            .setText(
                                stopInfos
                                    .stream()
                                    .map( stopInfo -> "🚋 Остановка: " + stopInfo.getTextInfo() )
                                    .collect(Collectors.joining("\n")))
                            .appendText("\nПоказать троллейбусы?");
                }
            }
        }

        return null;
    }
}
