package com.oybek.bridgevk.Entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Обёртка для текущих данных об остановке трамвая
 */
@Data
public class TramStopInfo {
    @SerializedName("tramStopName")
    private String name;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("tramInfoList")
    private List<TramArrivalData> arrivals;
}
