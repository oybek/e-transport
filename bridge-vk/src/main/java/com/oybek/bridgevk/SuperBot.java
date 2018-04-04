package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import org.springframework.stereotype.Component;

@Component
public class SuperBot {
    private QueueController queueController;

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
            // ... sleep 1 second
            try {
                Thread.sleep(1000);
            } catch( InterruptedException e ) {
                e.printStackTrace();
                return;
            }
        } else {
            // ... echo income message
            Message msg = queueController.getQueueToBot().poll();
            queueController.getQueueFromBot().add(msg);
        }

        work();
    }
}
