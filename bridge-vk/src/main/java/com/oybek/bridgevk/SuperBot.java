package com.oybek.bridgevk;

import com.google.gson.*;
import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.Entities.TramInfo;
import com.oybek.bridgevk.Entities.TramStopInfo;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        }).start();
    }

    // soon it will become class
    // TODO: here must be only business logic
    private Message getReaction(Message msg) {
        // TODO: override clone method and work with clone
        Message replyMsg = msg;

        Gson gson = new Gson();

        StringBuilder answer = new StringBuilder();

        // no geolocation provided
        if( msg.getGeo() == null ) {
            replyMsg.setText("Для того чтобы я мог найти ближайшую остановку, отправьте мне свои координаты, вот как это делается:");
            replyMsg.setAttachment("doc-163915852_464149858");
            return replyMsg;
        }

        // get info about tram stop
        String response = Courier.get(String.format(url, msg.getGeo().getLatitude(), msg.getGeo().getLongitude()));
        TramStopInfo tramStopInfo = gson.fromJson(response, TramStopInfo.class);

        if (tramStopInfo == null) {
            replyMsg.setText("Извините, не удалось найти информацию о трамваях 😞");
            return replyMsg;
        }

        // provide information
        replyMsg.setText("🚋 Ближайшая остановка: " + tramStopInfo.getTextInfo());

        double nearestTramStopLatitude = tramStopInfo.getLatitude();
        double nearestTramStopLongitude = tramStopInfo.getLongitude();

        String requestResult = Courier.get(String.format(urlGetDistance, msg.getGeo().getLatitude(), msg.getGeo().getLongitude(), nearestTramStopLatitude, nearestTramStopLongitude));
        final double farValue = 25.0;
        if( Double.parseDouble(requestResult) > farValue ) {
            response = Courier.get(String.format(urlGetNearestToNearest, msg.getGeo().getLatitude(), msg.getGeo().getLongitude()));
            TramStopInfo tramStop2Info = gson.fromJson(response, TramStopInfo.class);

            replyMsg.appendText("\n🚋 Другое направление: " + tramStop2Info.getTextInfo());
        }

        return replyMsg;
    }

    public void work () {
        while (true) {
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
                // ... get message from queue
                Message msg = queueController.getQueueToBot().poll();

                // get reaction of bot to message
                Message replyMsg = getReaction(msg);

                // url encode bot's response
                try {
                    replyMsg.setText(URLEncoder.encode(replyMsg.getText(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                // put bot's reply to outgoing queue
                queueController.getQueueFromBot().add(msg);
            }
        }
    }
}
