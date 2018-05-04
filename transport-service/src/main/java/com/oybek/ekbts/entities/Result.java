package com.oybek.ekbts.entities;

import java.util.List;

public class Result {
    private String stopType;
    private String stopName;
    private double latitude;
    private double longitude;
    private List<TramInfo> transportInfoList;

    public Result(List<TramInfo> transportInfoList) {
        this.transportInfoList = transportInfoList;
    }

    public String getStopName() {
        return stopName;
    }

    public Result setStopName(String stopName) {
        this.stopName = stopName;
        return this;
    }

    public List<TramInfo> getTransportInfoList() {
        return transportInfoList;
    }

    public void setTransportInfoList(List<TramInfo> transportInfoList) {
        this.transportInfoList = transportInfoList;
    }

    public String getStopType() {
        return stopType;
    }

    public Result setStopType(String stopType) {
        this.stopType = stopType;
        return this;
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
}
