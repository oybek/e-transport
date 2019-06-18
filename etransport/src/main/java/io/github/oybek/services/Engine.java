package io.github.oybek.services;

import com.sun.javafx.geom.Vec2f;
import io.github.oybek.entities.Stop;

import java.util.List;
import java.util.Optional;

public interface Engine {

    // searches for the stops by name
    List<Stop> findBusStopsByName(String name);
    List<Stop> findTramStopsByName(String name);
    List<Stop> findTrollStopsByName(String name);

    // returns name of the nearest stop in radius of 2km
    Optional<String> findNearestBusStops(Vec2f pos);
    Optional<String> findNearestTramStops(Vec2f pos);
    Optional<String> findNearestTrollStops(Vec2f pos);
}
