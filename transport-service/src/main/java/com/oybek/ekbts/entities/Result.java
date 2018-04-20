package com.oybek.ekbts.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Result {
    private String tramStopName;
    private double latitude;
    private double longitude;
    private List<TramInfo> tramInfoList;

    public Result(List<TramInfo> tramInfoList) {
        this.tramInfoList = tramInfoList;
    }
}
