package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.Entities.StopInfo;

public class Bot {
    private Ettu ettu;

    //
    Bot(Ettu ettu) {
        this.ettu = ettu;
    }

    // soon it will become class
    // TODO: here must be only business logic
    public Message getReaction(Message msg) {
        // TODO: override clone method and work with clone
        Message replyMsg = msg;

        // no geolocation provided
        if (msg.getGeo() == null) {
            replyMsg.setText("Для того чтобы я мог найти ближайшую остановку, отправьте мне свои координаты, вот как это делается:");
            replyMsg.setAttachment("doc-163915852_464149858");
            return replyMsg;
        }

        // get info about tram stop
        StopInfo stopInfo = ettu.getNearestTramStop(msg.getGeo());

        if (stopInfo == null) {
            replyMsg.setText("Извините, не удалось найти информацию о трамваях 😞");
            return replyMsg;
        }

        // provide information
        replyMsg.setText("🚋 Ближайшая остановка: " + stopInfo.getTextInfo());

        if (ettu.getDistance(stopInfo.getGeo(), msg.getGeo()) > 25.0) {
            StopInfo tramStop2Info = ettu.getNearestToNearestTramStop(msg.getGeo());
            replyMsg.appendText("\n🚋 Другое направление: " + tramStop2Info.getTextInfo());
        }

        return replyMsg;
    }

}
