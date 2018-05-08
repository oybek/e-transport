package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.Entities.StopInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Bot {
    private Ettu ettu;
    private ConcurrentLinkedQueue<Message> outgoingQueue;

    private Message message;

    private final Object monitor = new Object();

    //
    Bot(Ettu ettu, ConcurrentLinkedQueue<Message> outgoingQueue) {
        this.ettu = ettu;
        this.outgoingQueue = outgoingQueue;

        // run bot's logic in new thread
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    start();
                } catch (Exception e) {
                }
            }
        } ).start();
    }

    public void start() throws Exception {
        synchronized (monitor) {
            while (true) {
                monitor.wait(); // wait for new message

                // no geolocation provided
                if (message.getGeo() == null) {
                    send(
                        message
                            .clone()
                            .setText("Для того чтобы я мог найти ближайшую остановку, отправьте мне свои координаты, вот как это делается:")
                            .setAttachment("doc-163915852_464149858")
                    );
                    continue;
                }

                // get info about tram stop
                List<StopInfo> stopInfos = ettu.getNearestTramStops(message.getGeo(), 2);

                // if no tram stops ...
                if (stopInfos == null) {
                    // ... say about it, TODO: need error log
                    send(
                        message
                            .clone()
                            .setText("Извините, не удалось найти информацию о трамваях 😞")
                    );
                    continue;
                }

                send(
                    message
                        .clone()
                        .setText(
                            stopInfos
                                .stream()
                                .map( stopInfo -> "🚋 Остановка: " + stopInfo.getTextInfo() )
                                .collect(Collectors.joining("\n"))
                        )
                );
            }
        }
    }

    public void shake() {
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    // puts message to outgoing queue
    private void send(Message msg) {
        // url encode bot's response
        try {
            msg.setText(URLEncoder.encode(msg.getText(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // put bot's reply to outgoing queue
        outgoingQueue.add(msg);
    }
}
