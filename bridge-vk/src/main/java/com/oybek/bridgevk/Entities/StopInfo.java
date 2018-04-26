package com.oybek.bridgevk.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StopInfo {
    @SerializedName("stopType")
    private String stopType;

    @SerializedName("tramStopName")
    private String tramStopName;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("transportInfoList")
    private List<TransportInfo> transportInfoList;

    public String getTramStopName() {
        return tramStopName;
    }

    public void setTramStopName(String tramStopName) {
        this.tramStopName = tramStopName;
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

    public List<TransportInfo> getTransportInfoList() {
        return transportInfoList;
    }

    public void setTransportInfoList(List<TransportInfo> transportInfoList) {
        this.transportInfoList = transportInfoList;
    }

    public Geo getGeo() {
        return new Geo(latitude, longitude);
    }

    public String getTextInfo(String transportType) {
        // provide information
        StringBuffer answer = new StringBuffer();

        answer.append(String.format(getTramStopName() + "\n"));

        for (TransportInfo transportInfo : getTransportInfoList()) {
            long timeToReach = Long.parseLong(transportInfo.getTimeReach());
            answer.append(
                    timeToReach == 0
                            ? String.format("%s-й %s уже подъезжает\n", transportInfo.getRoute(), transportType)
                            : String.format("%s-й %s будет через %s мин.\n", transportInfo.getRoute(), transportType, transportInfo.getTimeReach())
            );
        }
        return answer.toString();
    }

    @Override
    public String toString() {
        return "StopInfo{" +
                "stopName='" + tramStopName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", transportInfoList=" + transportInfoList +
                '}';
    }
}
