package com.oybek.ekbts.entities;

import com.google.gson.annotations.SerializedName;

public class TramStop {
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

    @SerializedName("LONGITUDE")
    private double longitude;

    @SerializedName("LATITUDE")
    private double latitude;

    @SerializedName("LAT")
    private double lat;

    @SerializedName("LON")
    private double lon;

    @SerializedName("ACCURACY")
    private double accuracy;

    @Override
    public String toString() {
        return "TramStop{" +
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
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", lat=" + lat +
                ", lon=" + lon +
                ", accuracy='" + accuracy + '\'' +
                '}';
    }
}
