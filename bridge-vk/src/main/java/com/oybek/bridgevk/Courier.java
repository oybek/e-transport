package com.oybek.bridgevk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimerTask;

@RestController
public class Courier {
    @Value("${accessToken}")
    private String accessToken;
    private String sendMessageURL = "https://api.vk.com/method/messages.send?v=3.0&user_id={user_id}&message={message}&access_token={access_token}";
    private String getMessageUrl = "https://api.vk.com/method/messages.get?last_message_id={last_message_id}&access_token={access_token}&v=3";

    @Value("${artificialPing}")
    private long artificialPing = 1000;

    private QueueController queueController;

    private long lastMessageId = 0;

    public Courier( QueueController queueController ) {
        this.queueController = queueController;

        new Thread(new Runnable() {
            @Override
            public void run() {
                update();
            }
        }).start();
    }

    private void update() {
        System.out.println("hello, world\n");

        try {
            Thread.sleep(artificialPing);
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            update();
        }
    }
}
