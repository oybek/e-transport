package com.oybek.bridgevk;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oybek.bridgevk.Entities.Message;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class SuperBot {
    private QueueController queueController;

    // TODO: very bad need to refactor
    private String url = "http://localhost:8888/get_nearest?latitude=%f&longitude=%f";
    private String urlGetNearestToNearest = "http://localhost:8888/get_nearest_to_nearest?latitude=%f&longitude=%f";
    private String urlGetDistance = "http://localhost:8888/get_distance?lat1=%f&lon1=%f&lat2=%f&lon2=%f";

    public SuperBot(QueueController queueController) {
        this.queueController = queueController;

        new Thread(this::work).start();
    }

    // TODO: refactor this function, deserialize json before working with message
    // very bad bad ... bad code
    public void work() {
        while( true ) {
            // if no work ...
            if (queueController.getQueueToBot().isEmpty()) {
                // ... sleep 0.5 second
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            } else {
                // ... echo income message
                Message msg = queueController.getQueueToBot().poll();

                try {
                    JsonParser parser = new JsonParser();

                    StringBuilder answer = new StringBuilder();

                    if( msg.getGeo() == null ) {
                        answer.append("Для того чтобы я мог определить остановку отправьте ее геопозицию, или вашу текущую если вы уже на остановке");
                    } else {
                        JsonElement jsonElement = parser.parse(Courier.get(String.format(url, msg.getGeo().getLatitude(), msg.getGeo().getLongitude())));

                        if(jsonElement.getAsJsonObject().get("tramInfoList").isJsonNull()) {
                            answer.append("Извините, не удалось найти информацию о трамваях 😞");
                        } else {
                            answer.append( "Ближайшая остановка: " + jsonElement.getAsJsonObject().get("tramStopName").getAsString() + "\n" );
                            JsonArray jsonArray = jsonElement.getAsJsonObject().get("tramInfoList").getAsJsonArray();
                            for (JsonElement element : jsonArray) {
                                if (element.isJsonObject()) {
                                    JsonObject jObj = element.getAsJsonObject();

                                    long timeToReach = jObj.get("timeReach").getAsLong();
                                    if( timeToReach == 0 ) {
                                        answer.append(jObj.get("route").getAsString() + "-й трамвай будет меньше, чем через минуту\n" );
                                    }
                                    else {
                                        answer.append(jObj.get("route").getAsString() + "-й трамвай будет через " + jObj.get("timeReach").getAsString() + " мин.\n");
                                    }
                                }
                            }

                            double nearestTramStopLatitude = jsonElement.getAsJsonObject().get("latitude").getAsDouble();
                            double nearestTramStopLongitude = jsonElement.getAsJsonObject().get("longitude").getAsDouble();

                            String requestResult = Courier.get(String.format(urlGetDistance, msg.getGeo().getLatitude(), msg.getGeo().getLongitude(), nearestTramStopLatitude, nearestTramStopLongitude));
                            final double farValue = 25.0;
                            if( Double.parseDouble(requestResult) > farValue ) {
                                jsonElement = parser.parse(Courier.get(String.format(urlGetNearestToNearest, msg.getGeo().getLatitude(), msg.getGeo().getLongitude())));

                                answer.append( "\nДругое направление: " + jsonElement.getAsJsonObject().get("tramStopName").getAsString() + "\n" );
                                jsonArray = jsonElement.getAsJsonObject().get("tramInfoList").getAsJsonArray();
                                for (JsonElement element : jsonArray) {
                                    if (element.isJsonObject()) {
                                        JsonObject jObj = element.getAsJsonObject();

                                        long timeToReach = jObj.get("timeReach").getAsLong();
                                        if( timeToReach == 0 ) {
                                            answer.append(jObj.get("route").getAsString() + "-й трамвай будет меньше, чем через минуту\n" );
                                        }
                                        else {
                                            answer.append(jObj.get("route").getAsString() + "-й трамвай будет через " + jObj.get("timeReach").getAsString() + " мин.\n");
                                        }
                                    }
                                }
                            }
                        }
                    }

                    msg.setText(URLEncoder.encode(answer.toString(), "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                queueController.getQueueFromBot().add(msg);
            }
        }
    }
}
