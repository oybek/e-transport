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
        // if no work ...
        if( queueController.getQueueToBot().isEmpty() ) {
            // ... sleep 0.5 second
            try {
                Thread.sleep(500);
            } catch( InterruptedException e ) {
                e.printStackTrace();
                return;
            }
        } else {
            // ... echo income message
            Message msg = queueController.getQueueToBot().poll();

            try {
                JsonParser parser = new JsonParser();

                System.out.println( Courier.get( String.format(url, msg.getGeo().getLatitude(), msg.getGeo().getLongitude()) ) );

                StringBuilder answer = new StringBuilder();

                if( msg.getGeo() != null ) {
                    JsonArray jsonArray = parser.parse(Courier.get(String.format(url, msg.getGeo().getLatitude(), msg.getGeo().getLongitude()))).getAsJsonArray();
                    for (JsonElement element : jsonArray) {
                        if (element.isJsonObject()) {
                            JsonObject jObj = element.getAsJsonObject();
                            answer.append( jObj.get("route").getAsString() + "-й трамвай будет через " + jObj.get("timeReach").getAsString() + " минут\n" );
                        }
                    }

                    msg.setText( URLEncoder.encode(answer.toString(), "UTF-8") );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            queueController.getQueueFromBot().add(msg);
        }

        work();
    }
}
