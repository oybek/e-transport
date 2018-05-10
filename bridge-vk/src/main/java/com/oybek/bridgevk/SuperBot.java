package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Component
public class SuperBot {
    private QueueController queueController;
    private Ettu ettu;
    private HashMap<Long, Bot> bots;

    public SuperBot(QueueController queueController, Ettu ettu) {
        this.queueController = queueController;
        this.ettu = ettu;
        bots = new HashMap<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        }).start();
    }

    public void work () {
        while (true) {
            // if no work ...
            if (queueController.getQueueToBot().isEmpty()) {
                // ... sleep 0.5 second
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            } else {
                // ... get message from queue
                Message msg = queueController.getQueueToBot().poll();

                // get reaction of bot to message
                bots.putIfAbsent(msg.getUid(), new Bot(ettu));
                Message replyMsg = bots.get(msg.getUid()).getReaction(msg);

                // url encode bot's response
                try {
                    replyMsg.setText(URLEncoder.encode(replyMsg.getText(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                // put bot's reply to outgoing queue
                queueController.getQueueFromBot().add(replyMsg);
            }
        }
    }
}
