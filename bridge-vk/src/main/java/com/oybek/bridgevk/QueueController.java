package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Подобие менеджера очередей
 */
@Component
public class QueueController {
    private ConcurrentLinkedQueue<Message> queueToBot;
    private ConcurrentLinkedQueue<Message> queueFromBot;

    public QueueController() {
        queueToBot = new ConcurrentLinkedQueue<>();
        queueFromBot = new ConcurrentLinkedQueue<>();
    }

    ConcurrentLinkedQueue<Message> getQueueToBot() {
        return queueToBot;
    }

    ConcurrentLinkedQueue<Message> getQueueFromBot() {
        return queueFromBot;
    }
}
