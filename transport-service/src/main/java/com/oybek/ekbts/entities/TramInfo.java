package com.oybek.ekbts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TramInfo {
    private String route;
    private String timeReach;
    private String distanceReach;

    @Override
    public String toString() {
        return "TramInfo{" +
                "route='" + route + '\'' +
                ", timeReach='" + timeReach + '\'' +
                ", distanceReach='" + distanceReach + '\'' +
                '}';
    }
}
