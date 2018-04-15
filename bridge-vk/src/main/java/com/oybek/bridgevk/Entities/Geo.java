package com.oybek.bridgevk.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Географическая позиция
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {
    /**
     * Широта
     */
    private double latitude;
    /**
     * Долгота
     */
    private double longitude;

    @Override
    public String toString() {
        return "Geo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
