package com.oybek.bridgevk.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StopInfo {
    @SerializedName("stopType")
    private String stopType;

    @SerializedName("stopName")
    private String stopName;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("transportInfoList")
    private List<TransportInfo> transportInfoList;

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
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

    public String getTextInfo() {
        // provide information
        StringBuffer answer = new StringBuffer();

        answer.append(String.format(getStopName() + "\n"));

        for (TransportInfo transportInfo : getTransportInfoList()) {
            long timeToReach = Long.parseLong(transportInfo.getTimeReach());
            if (stopType.equals("tram")) {
                answer.append( timeToReach == 0
                        ? String.format("%s-й трамвай уже подъезжает\n", transportInfo.getRoute())
                        : String.format("%s-й трамвай будет через %s мин.\n", transportInfo.getRoute(), transportInfo.getTimeReach())
                );
            }
            else if (stopType.equals("troll")) {
                answer.append( timeToReach == 0
                        ? String.format("%s-й тролейбус уже подъезжает\n", transportInfo.getRoute())
                        : String.format("%s-й тролейбус будет через %s мин.\n", transportInfo.getRoute(), transportInfo.getTimeReach())
                );
            }
        }
        return answer.toString();
    }

    @Override
    public String toString() {
        return "StopInfo{" +
                "stopName='" + stopName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", transportInfoList=" + transportInfoList +
                '}';
    }
}
