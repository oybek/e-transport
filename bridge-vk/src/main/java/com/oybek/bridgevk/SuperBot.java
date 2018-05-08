package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import org.springframework.stereotype.Component;

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
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            } else {
                // ... get message from queue
                Message msg = queueController.getQueueToBot().poll();

                // create bot, when it creates it runs in new thread
                bots.putIfAbsent(msg.getUid(), new Bot(ettu, queueController.getQueueFromBot()));

                // give msg to bot
                bots.get(msg.getUid()).setMessage(msg);

                // shake bot in order to make it work
                bots.get( msg.getUid() ).shake();
            }
        }
    }
}
