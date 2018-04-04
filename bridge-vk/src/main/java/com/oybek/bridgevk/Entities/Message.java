package com.oybek.bridgevk.Entities;

import com.sun.javafx.geom.Vec2d;

public class Message {
    private long id;
    private long date;
    private long uid;
    private long readState;
    private String text;
    private Geo geo;

    public Message(long id, long date, long uid, long readState, String text, Geo geo) {
        this.id = id;
        this.date = date;
        this.uid = uid;
        this.readState = readState;
        this.text = text;
        this.geo = geo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getReadState() {
        return readState;
    }

    public void setReadState(long readState) {
        this.readState = readState;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", date=" + date +
                ", uid=" + uid +
                ", readState=" + readState +
                ", text='" + text + '\'' +
                ", geo=" + ( geo == null ? geo : geo.toString() ) +
                '}';
    }
}
