package com.oybek.ekbts;

import com.google.gson.Gson;
import com.oybek.ekbts.entities.TramStop;
import com.sun.javafx.geom.Vec2d;
import javafx.application.Platform;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class Engine {
    ArrayList<TramStop> tramStops;

    public Engine() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(getResourceFile("json/tram-stops.json"))) {
            // Convert JSON to Java Object
            TramStop[] tramStopsRaw = gson.fromJson(reader, TramStop[].class);
            tramStops = new ArrayList<>(Arrays.asList(tramStopsRaw));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TramStop getNearest(Vec2d coord) {
        if (tramStops.size() == 0) {
            System.out.println("No single tram stop given");
            return null;
        }

        TramStop nearestTramStop = tramStops.get(0);
        for (TramStop currentTramStop : tramStops) {
            if (coord.distanceSq(currentTramStop.getLatitude(), currentTramStop.getLongitude())
                    < coord.distanceSq(nearestTramStop.getLatitude(), nearestTramStop.getLongitude())) {
                nearestTramStop = currentTramStop;
            }
        }

        return nearestTramStop;
    }

    public double getDistance( double lat1, double lon1, double lat2, double lon2 ) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public TramStop getNearestToNearest(Vec2d coord) {
        TramStop target = getNearest(coord);
        if( target == null ) {
            System.out.println("No single tram stop given");
            return null;
        }

        TramStop nearestToNearest = null;

        // choose first not target
        for (TramStop current : tramStops) {
            if (current.getId().equals(target.getId()) == false)
                nearestToNearest = current;
        }


        for (TramStop current : tramStops) {
            if( nearestToNearest == null ) {
                nearestToNearest = current;
                continue;
            }

            // skip target
            if (current.getId().equals(target.getId()) == false) {
                // if distance from current to nearest is less
                if (distance(current, target) < distance(nearestToNearest, target))
                    nearestToNearest = current;
            }
        }

        return nearestToNearest;
    }

    public File getResourceFile(String fileName) {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }

    private double distance( TramStop stop1, TramStop stop2 ) {
        if( stop1 == null || stop2 == null ) {
            return 0.0f;
        }
        return stop1.getCoord().distanceSq(stop2.getCoord());
    }

}
