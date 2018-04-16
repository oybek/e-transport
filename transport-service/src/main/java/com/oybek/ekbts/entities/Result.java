package com.oybek.ekbts.entities;

import com.oybek.ekbts.entities.TramInfo;

import java.util.List;

public class Result {
    private String tramStopName;
    private double latitude;
    private double longitude;
    private List<TramInfo> tramInfoList;

    public Result(List<TramInfo> tramInfoList) {
        this.tramInfoList = tramInfoList;
    }

    public String getTramStopName() {
        return tramStopName;
    }

    public void setTramStopName(String tramStopName) {
        this.tramStopName = tramStopName;
    }

    public List<TramInfo> getTramInfoList() {
        return tramInfoList;
    }

    public void setTramInfoList(List<TramInfo> tramInfoList) {
        this.tramInfoList = tramInfoList;
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
