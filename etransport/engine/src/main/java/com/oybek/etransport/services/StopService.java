package com.oybek.etransport.services;

import com.oybek.etransport.entities.Stop;
import com.oybek.etransport.entities.User;
import com.sun.javafx.geom.Vec2f;

import java.util.List;
import java.util.Optional;

public interface StopService {

    // searches for the stops by name
    List<Stop> findBusStopsByName(String name);
    List<Stop> findTramStopsByName(String name);
    List<Stop> findTrollStopsByName(String name);

    // returns name of the nearest stop in radius of 2km
    Optional<String> findNearestBusStops(Vec2f pos);
    Optional<String> findNearestTramStops(Vec2f pos);
    Optional<String> findNearestTrollStops(Vec2f pos);
}
