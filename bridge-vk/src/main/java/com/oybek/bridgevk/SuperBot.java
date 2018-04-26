package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.Entities.StopInfo;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class SuperBot {
    private QueueController queueController;
    private Ettu ettu;

    public SuperBot(QueueController queueController, Ettu ettu) {
        this.queueController = queueController;
        this.ettu = ettu;

        new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        }).start();
    }

    // soon it will become class
    // TODO: here must be only business logic
    private Message getReaction (Message msg) {
        // TODO: override clone method and work with clone
        Message replyMsg = msg;

        // no geolocation provided
        if (msg.getGeo() == null) {
            replyMsg.setText("Для того чтобы я мог найти ближайшую остановку, отправьте мне свои координаты, вот как это делается:");
            replyMsg.setAttachment("doc-163915852_464149858");
            return replyMsg;
        }

        // get info about tram stop
        StopInfo stopInfo = ettu.getNearestTrollStop(msg.getGeo());

        if (stopInfo == null) {
            replyMsg.setText("Извините, не удалось найти информацию о трамваях 😞");
            return replyMsg;
        }

        // provide information
        replyMsg.setText("🚋 Ближайшая остановка: " + stopInfo.getTextInfo());

        if (ettu.getDistance(stopInfo.getGeo(), msg.getGeo()) > 25.0) {
            StopInfo tramStop2Info = ettu.getNearestToNearestTrollStop(msg.getGeo());
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
