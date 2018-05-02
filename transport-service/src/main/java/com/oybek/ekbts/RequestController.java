package com.oybek.ekbts;

import com.oybek.ekbts.entities.Result;
import com.oybek.ekbts.entities.Stop;
import com.sun.javafx.geom.Vec2d;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<Result> getTramByName(@RequestParam("name") String name) {
        List<Stop> matchedStops = engine.getTramStopByName(name);

        List<Result> results = new ArrayList<>();

        matchedStops.forEach(stop -> {
            Result result = ettu.getInfo(stop);
            result.setStopType("tram");
            result.setStopName(stop.getName() + "(" + stop.getDirection() + ")");

            results.add(result);
        });

        return results;
    }

    @GetMapping(value = "/troll_stops/get_nearest")
    public Result getNearestTrollStop(@RequestParam("latitude") double latitude
            , @RequestParam("longitude") double longitude ) {
        Stop trollStop = engine.getNearestTrollStop( new Vec2d( latitude, longitude ) );

        Result result = ettu.getInfo(trollStop);
        result.setStopType("troll");
        result.setStopName(trollStop.getName() + " (" + trollStop.getDirection() + ")" );

        return result;
    }

    @GetMapping(value = "/troll_stops/get_nearest_to_nearest")
    public Result getNearestToNearestTrollStop(@RequestParam("latitude") double latitude
            , @RequestParam("longitude") double longitude ) {
        Stop trollStop = engine.getNearestToNearestTrollStop( new Vec2d( latitude, longitude ) );

        Result result = ettu.getInfo(trollStop);
        result.setStopType("troll");
        result.setStopName(trollStop.getName() + " (" + trollStop.getDirection() + ")");

        return result;
    }

    @GetMapping(value = "/tram_stops/get_nearest")
    public Result getNearestTramStop(@RequestParam("latitude") double latitude
            , @RequestParam("longitude") double longitude ) {
        Stop tramStop = engine.getNearestTramStop( new Vec2d( latitude, longitude ) );

        Result result = ettu.getInfo(tramStop);
        result.setStopType("tram");
        result.setStopName(tramStop.getName() + " (" + tramStop.getDirection() + ")" );

        return result;
    }

    @GetMapping(value = "/tram_stops/get_nearest_to_nearest")
    public Result getNearestToNearest(@RequestParam("latitude") double latitude
            , @RequestParam("longitude") double longitude ) {
        Stop tramStop = engine.getNearestToNearestTramStop( new Vec2d( latitude, longitude ) );

        Result result = ettu.getInfo(tramStop);
        result.setStopType("tram");
        result.setStopName(tramStop.getName() + " (" + tramStop.getDirection() + ")");

        return result;
    }

    @GetMapping(value = "/get_distance")
    public double getDistance(
        @RequestParam("lat1") double lat1,
        @RequestParam("lon1") double lon1,
        @RequestParam("lat2") double lat2,
        @RequestParam("lon2") double lon2 )
    {
        return engine.getDistance(lat1, lon1, lat2, lon2);
    }
}
