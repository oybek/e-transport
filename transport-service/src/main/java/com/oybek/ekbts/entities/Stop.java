package com.oybek.ekbts.entities;

import com.google.gson.annotations.SerializedName;
import com.sun.javafx.geom.Vec2d;

public class Stop {
    @SerializedName("ID")
    private String id;

    @SerializedName("NAME")
    private String name;

    @SerializedName("NOTE")
    private String note;

    @SerializedName("STATUS")
    private String status;

    @SerializedName("X")
    private double x;

    @SerializedName("Y")
    private double y;

    @SerializedName("ATTACHED_TO")
    private String attachedTo;

    @SerializedName("SMS_CODE")
    private String smsCode;

    @SerializedName("ANGLE")
    private double angle;

    @SerializedName("ALIGN")
    private String align;

    @SerializedName("TTU_CODE")
    private String ttuCode;

    @SerializedName("DIRECTION")
    private String direction;

    @SerializedName("TR_LAYER")
    private String trLayer;

    @SerializedName("TTU_IDS")
    private String ttuIds;

    @SerializedName("LATITUDE")
    private double lon;

    @SerializedName("LONGITUDE")
    private double lat;

    @SerializedName("LAT")
    private double latitude;

    @SerializedName("LON")
    private double longitude;

    @SerializedName("ACCURACY")
    private double accuracy;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDirection() { return direction; }

    public void setDirection(String direction) { this.direction = direction; }

    public Vec2d getCoord() { return new Vec2d(latitude, longitude); }

    @Override
    public String toString() {
        return "Stop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", attachedTo='" + attachedTo + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", angle='" + angle + '\'' +
                ", align='" + align + '\'' +
                ", ttuCode='" + ttuCode + '\'' +
                ", direction='" + direction + '\'' +
                ", trLayer='" + trLayer + '\'' +
                ", ttuIds='" + ttuIds + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", lat=" + lat +
                ", lon=" + lon +
                ", accuracy='" + accuracy + '\'' +
                '}';
    }
}
