package com.oybek.ekbts.entities;

import java.util.List;

public class Result {
    private List<TramInfo> tramInfoList;

    public Result(List<TramInfo> tramInfoList) {
        this.tramInfoList = tramInfoList;
    }

    public List<TramInfo> getTramInfoList() {
        return tramInfoList;
    }

    public void setTramInfoList(List<TramInfo> tramInfoList) {
        this.tramInfoList = tramInfoList;
    }
}
