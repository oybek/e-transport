package com.oybek.ekbts;

import com.oybek.ekbts.entities.Result;
import com.oybek.ekbts.entities.Stop;
import com.sun.javafx.geom.Vec2d;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class RequestController {

    private Ettu ettu;
    private Engine engine;

    public RequestController(Ettu ettu, Engine engine) {
        this.ettu = ettu;
        this.engine = engine;
    }

    @GetMapping(value = "/test")
    public String getInfo() {
        return "ok";
    }

    @GetMapping(value = "/tram_stops/get")
    public List<Result> getTramByName(
        @RequestParam("name") String name
    ) {
        List<Stop> matchedStops = engine.getTramStopByName(name);

        return matchedStops
                .stream()
                .map(stop -> ettu
                    .getInfo(stop)
                    .setStopType("tram")
                    .setStopName(stop.getName() + "(" + stop.getDirection() + ")"))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/troll_stops/get")
    public List<Result> getTrollByName(
            @RequestParam("name") String name
    ) {
        List<Stop> matchedStops = engine.getTrollStopByName(name);

        return matchedStops
                .stream()
                .map(stop -> ettu
                        .getInfo(stop)
                        .setStopType("troll")
                        .setStopName(stop.getName() + "(" + stop.getDirection() + ")"))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/troll_stops/get_nearest")
    public List<Result> getNearestTrollStop(
        @RequestParam("latitude") double latitude
        , @RequestParam("longitude") double longitude
        , @RequestParam("n") int n
    ) {
        Stream<Stop> trollStops = engine.getNearestTrollStops( new Vec2d(latitude, longitude), n );

        return trollStops
                .map( stop -> ettu.getInfo(stop).setStopType("troll") )
                .collect( Collectors.toList() );
    }

    @GetMapping(value = "/tram_stops/get_nearest")
    public List<Result> getNearestTramStop(
        @RequestParam("latitude") double latitude
        , @RequestParam("longitude") double longitude
        , @RequestParam("n") int n
    ) {
        Stream<Stop> tramStops = engine.getNearestTramStops( new Vec2d(latitude, longitude), n );

        return tramStops
                .map( stop -> ettu.getInfo(stop).setStopType("tram") )
                .collect( Collectors.toList() );
    }

    @GetMapping(value = "/get_distance")
    public double getDistance(
        @RequestParam("lat1") double lat1
        , @RequestParam("lon1") double lon1
        , @RequestParam("lat2") double lat2
        , @RequestParam("lon2") double lon2 )
    {
        return engine.getDistance(lat1, lon1, lat2, lon2);
    }
}
