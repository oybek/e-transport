package com.oybek.ekbts.entities;

public class TramInfo {
    private String route;
    private String timeReach;
    private String distanceReach;

    public TramInfo() {
    }

    public TramInfo(String route, String timeReach, String distanceReach) {
        this.route = route;
        this.timeReach = timeReach;
        this.distanceReach = distanceReach;
    }

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
