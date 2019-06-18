package io.github.oybek.services;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec2f;
import io.github.oybek.entities.Stop;

import java.util.List;
import java.util.Optional;

public interface StopService {

    // searches for the stops by name
    List<Stop> findBusStopsByName(String name);
    List<Stop> findTramStopsByName(String name);
    List<Stop> findTrollStopsByName(String name);

    Boolean isBusStop(String name);
    Boolean isTramStop(String name);
    Boolean isTrollStop(String name);

    // returns name of the nearest stop in radius of 2km
    Stop findNearestBusStops(Vec2d pos);
    Stop findNearestTramStops(Vec2d pos);
    Stop findNearestTrollStops(Vec2d pos);
}
