package com.oybek.bridgevk.Entities;

import com.google.gson.annotations.SerializedName;

public class TramInfo {
    @SerializedName("route")
    private String route;

    @SerializedName("timeReach")
    private String timeReach;

    @SerializedName("distanceReach")
    private String distanceReach;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTimeReach() {
        return timeReach;
    }

    public void setTimeReach(String timeReach) {
        this.timeReach = timeReach;
    }

    public String getDistanceReach() {
        return distanceReach;
    }

    public void setDistanceReach(String distanceReach) {
        this.distanceReach = distanceReach;
    }

    @Override
    public String toString() {
        return "TramInfo{" +
                "route='" + route + '\'' +
                ", timeReach='" + timeReach + '\'' +
                ", distanceReach='" + distanceReach + '\'' +
                '}';
    }
}
