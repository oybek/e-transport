package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class QueueController {
    ConcurrentLinkedQueue<Message> queueToBot;
    ConcurrentLinkedQueue<Message> queueFromBot;

    public QueueController() {
        queueToBot = new ConcurrentLinkedQueue<>();
        queueFromBot = new ConcurrentLinkedQueue<>();
    }

    public ConcurrentLinkedQueue<Message> getQueueToBot() {
        return queueToBot;
    }

    public ConcurrentLinkedQueue<Message> getQueueFromBot() {
        return queueFromBot;
    }
}
