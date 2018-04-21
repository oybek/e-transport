package com.oybek.bridgevk.utils;

import com.google.gson.Gson;
import com.oybek.bridgevk.Courier;
import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.Entities.TramArrivalData;
import com.oybek.bridgevk.Entities.TramStopInfo;

import java.io.IOException;
import java.util.List;

/**
 * Парсер ответа VK API
 */
public class ResponseParser {
    private final static String URL = "http://localhost:8888/get_nearest?latitude=%f&longitude=%f";
    private final static String URL_GET_NEAREST_TO_NEAREST = "http://localhost:8888/get_nearest_to_nearest?latitude=%f&longitude=%f";
    private final static String URL_GET_DISTANCE = "http://localhost:8888/get_distance?lat1=%f&lon1=%f&lat2=%f&lon2=%f";
    private final static String NOT_FOUND_MESSAGE = "Извините, не удалось найти информацию о трамваях 😞";
    private final static double DISTANCE_LIMIT = 25.0;
    private final static Gson GSON = new Gson();

    /**
     * Десериализакция сообщения
     *
     * @param message - сообщение
     * @return сообщение в читаемом виде
     * @throws IOException - возможны ошибки при неудачных обращениях к API
     */
    public static String deserialize(Message message) throws IOException {
        StringBuilder answer = new StringBuilder();

        TramStopInfo tramStopInfo = GSON.fromJson(Courier.get(String.format(URL, message.getGeo().getLatitude(),
                message.getGeo().getLongitude())), TramStopInfo.class);

        if (tramStopInfo.getArrivals().isEmpty()) {
            answer.append(NOT_FOUND_MESSAGE);
            return answer.toString();
        }

        answer.append("Ближайшая остановка: ").append(tramStopInfo.getName()).append("\n");
        answer.append(operateDirection(tramStopInfo.getArrivals()));

        Double distance = Double.valueOf(Courier.get(String.format(URL_GET_DISTANCE, message.getGeo().getLatitude(),
                message.getGeo().getLongitude(), tramStopInfo.getLatitude(), tramStopInfo.getLongitude())));

        if (distance > DISTANCE_LIMIT) {
            tramStopInfo = GSON.fromJson(Courier.get(String.format(URL_GET_NEAREST_TO_NEAREST,
                    message.getGeo().getLatitude(), message.getGeo().getLongitude())), TramStopInfo.class);
            answer.append("\nДругое направление: ").append(tramStopInfo.getName()).append("\n");
            answer.append(operateDirection(tramStopInfo.getArrivals()));
        }

        return answer.toString();
    }

    /**
     * Распознание данных о направлении
     *
     * @param arrivals - массив данных о прибывании трамваев
     * @return сообщение о приблизительном времени ожидания трамвая
     */
    private static String operateDirection(List<TramArrivalData> arrivals) {
        StringBuilder builder = new StringBuilder();
        arrivals.forEach(arrivalDate -> {
            long timeToReach = arrivalDate.getTimeToReach();

            if (timeToReach == 0) {
                builder.append(arrivalDate.getRoute()).append("-й трамвай будет меньше, чем через минуту\n");
            } else {
                builder.append(arrivalDate.getRoute()).append("-й трамвай будет через ")
                        .append(arrivalDate.getTimeToReach()).append(" мин.\n");
            }
        });
        return builder.toString();
    }
}
