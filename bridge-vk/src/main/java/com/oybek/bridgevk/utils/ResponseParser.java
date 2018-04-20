package com.oybek.bridgevk.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oybek.bridgevk.Courier;
import com.oybek.bridgevk.Entities.Message;

import java.io.IOException;

/**
 * Парсер ответа VK API
 */
public class ResponseParser {
    private final static String URL = "http://localhost:8888/get_nearest?latitude=%f&longitude=%f";
    private final static String URL_GET_NEAREST_TO_NEAREST = "http://localhost:8888/get_nearest_to_nearest?latitude=%f&longitude=%f";
    private final static String URL_GET_DISTANCE = "http://localhost:8888/get_distance?lat1=%f&lon1=%f&lat2=%f&lon2=%f";
    private final static String NOT_FOUND_MESSAGE = "Извините, не удалось найти информацию о трамваях 😞";
    private final static JsonParser parser = new JsonParser();

    /**
     * Десериализакция сообщения
     *
     * @param message - сообщение
     * @return сообщение в читаемом виде
     * @throws IOException - возможны ошибки при неудачных обращениях к API
     */
    public static String deserialize(Message message) throws IOException {
        StringBuilder answer = new StringBuilder();
        JsonElement jsonElement = parser.parse(Courier.get(String.format(URL, message.getGeo().getLatitude(), message.getGeo().getLongitude())));

        if (jsonElement.getAsJsonObject().get("tramInfoList").isJsonNull()) {
            answer.append(NOT_FOUND_MESSAGE);
        } else {
            answer.append("Ближайшая остановка: ").append(jsonElement.getAsJsonObject().get("tramStopName").getAsString()).append("\n");
            JsonArray jsonArray = jsonElement.getAsJsonObject().get("tramInfoList").getAsJsonArray();
            answer.append(operateDirection(jsonArray));

            double nearestTramStopLatitude = jsonElement.getAsJsonObject().get("latitude").getAsDouble();
            double nearestTramStopLongitude = jsonElement.getAsJsonObject().get("longitude").getAsDouble();

            String requestResult = Courier.get(String.format(URL_GET_DISTANCE, message.getGeo().getLatitude(),
                    message.getGeo().getLongitude(), nearestTramStopLatitude, nearestTramStopLongitude));
            final double farValue = 25.0;
            if (Double.parseDouble(requestResult) > farValue) {
                jsonElement = parser.parse(Courier.get(String.format(URL_GET_NEAREST_TO_NEAREST,
                        message.getGeo().getLatitude(), message.getGeo().getLongitude())));

                answer.append("\nДругое направление: ").append( jsonElement.getAsJsonObject().get("tramStopName").getAsString()).append("\n");
                jsonArray = jsonElement.getAsJsonObject().get("tramInfoList").getAsJsonArray();
                answer.append(operateDirection(jsonArray));
            }
        }
        return answer.toString();
    }

    /**
     * Распознание данных о направлении
     *
     * @param jsonArray - массив данных
     * @return сообщение о приблизительном времени ожидания трамвая
     */
    private static String operateDirection(JsonArray jsonArray) {
        StringBuilder builder = new StringBuilder();
        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jObj = element.getAsJsonObject();

                long timeToReach = jObj.get("timeReach").getAsLong();

                if (timeToReach == 0) {
                    builder.append(jObj.get("route").getAsString()).append("-й трамвай будет меньше, чем через минуту\n");
                } else {
                    builder.append(jObj.get("route").getAsString()).append("-й трамвай будет через ")
                            .append(jObj.get("timeReach").getAsString()).append(" мин.\n");
                }
            }
        }
        return builder.toString();
    }
}
