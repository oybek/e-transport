package com.oybek.bridgevk.Entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Обёртка для данных о времени прибывания трамвая
 */
@Data
public class TramArrivalData {
    @SerializedName("route")
    private String route;

    @SerializedName("timeReach")
    private Long timeToReach;
}
