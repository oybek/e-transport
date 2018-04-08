package com.oybek.ekbts.entities;

import com.oybek.ekbts.entities.TramInfo;

import java.util.List;

public class Result {
    private String tramStopName;
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
}
