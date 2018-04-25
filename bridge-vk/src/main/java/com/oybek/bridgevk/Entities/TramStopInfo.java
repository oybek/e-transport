package com.oybek.bridgevk.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TramStopInfo {
    @SerializedName("tramStopName")
    private String tramStopName;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("tramInfoList")
    private List<TramInfo> tramInfoList;

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

    public List<TramInfo> getTramInfoList() {
        return tramInfoList;
    }

    public void setTramInfoList(List<TramInfo> tramInfoList) {
        this.tramInfoList = tramInfoList;
    }

    public String getTextInfo() {
        // provide information
        StringBuffer answer = new StringBuffer();

        answer.append(String.format(getTramStopName() + "\n"));

        for (TramInfo tramInfo : getTramInfoList()) {
            long timeToReach = Long.parseLong(tramInfo.getTimeReach());
            answer.append(
                    timeToReach == 0
                            ? tramInfo.getRoute() + "-й трамвай уже подъезжает\n"
                            : tramInfo.getRoute() + "-й трамвай будет через " + tramInfo.getTimeReach() + " мин.\n"
            );
        }
        return answer.toString();
    }

    @Override
    public String toString() {
        return "TramStopInfo{" +
                "tramStopName='" + tramStopName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", tramInfoList=" + tramInfoList +
                '}';
    }
}
