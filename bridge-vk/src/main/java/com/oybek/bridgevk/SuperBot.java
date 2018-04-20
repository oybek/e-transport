package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.utils.ResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class SuperBot {
    private QueueController queueController;
    private static final String NOT_FOUND_MESSAGE = "Для того чтобы я мог определить остановку отправьте ее геопозицию, или вашу текущую если вы уже на остановке";

    @Autowired
    public SuperBot(QueueController controller) {
        queueController = controller;
    }

    /**
     * Периодическая проверка очередей сообщений
     */
    @Scheduled(fixedDelay = 500, initialDelay = 1000)
    private void check() {
        if (queueController.queueToBot.isEmpty()) {
            return;
        }
        Message msg = queueController.getQueueToBot().poll();
        if (msg == null) {
            return;
        }
        try {
            String answer = msg.getGeo() == null
                    ? NOT_FOUND_MESSAGE
                    : ResponseParser.deserialize(msg);
            msg.setText(URLEncoder.encode(answer, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        queueController.getQueueFromBot().add(msg);
    }
}
