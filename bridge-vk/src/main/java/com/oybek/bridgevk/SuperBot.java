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

    private String url = "http://localhost:8888/get?latitude=%f&longitude=%f";

    public SuperBot(QueueController queueController) {
        this.queueController = queueController;

        new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        }).start();
    }

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
