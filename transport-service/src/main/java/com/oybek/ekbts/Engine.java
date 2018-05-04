package com.oybek.ekbts;

import com.google.gson.Gson;
import com.oybek.ekbts.algorithms.Levenshtein;
import com.oybek.ekbts.entities.Stop;
import com.sun.javafx.geom.Vec2d;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Engine {
    private ArrayList<Stop> tramStops;
    private ArrayList<Stop> trollStops;

    public Engine() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(getResourceFile("json/tram-stops.json"))) {
            // Convert JSON to Java Object
            Stop[] tramStopsRaw = gson.fromJson(reader, Stop[].class);
            tramStops = new ArrayList<>(Arrays.asList(tramStopsRaw));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Reader reader = new FileReader(getResourceFile("json/troll-stops.json"))) {
            // Convert JSON to Java Object
            Stop[] trollStopsRaw = gson.fromJson(reader, Stop[].class);
            trollStops = new ArrayList<>(Arrays.asList(trollStopsRaw));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //

    public List<Stop> getTramStopByName(String name) {
        return getByName(tramStops, name);
    }

    // Prepares string before Levenshtein calculations
    private String prepare(String s) {
        return s
                .trim()
                .toLowerCase()
                .replaceAll("пл\\.", "")
                .replaceAll("ст\\.", "")
                .replaceAll("г\\.", "")
                .replaceAll("гост\\.", "")
                .replaceAll("пер\\.", "")
                .replaceAll("м\\.", "")
                .replaceAll( "[^0-9а-я]", "" )
        ;
    }

    private List<Stop> getByName(List<Stop> stops, String name) {
        final String namePrepared = prepare(name);
        final int maxMistakeNum = name.length() / 2;

        // get tram stops sorted by match level
        List<Pair<Integer, Stop>> sortedStops = stops
                .stream()
                .map( stop -> new Pair<>(Levenshtein.calc(prepare(stop.getName()), namePrepared), stop) )
                .sorted( Comparator.comparing(Pair<Integer, Stop>::getKey) )
                .collect(Collectors.toList());

        final int minMistakeNum = sortedStops.get(0).getKey();

        System.out.println( String.format( "min mistake num: %d", minMistakeNum ) );

        // if most matched string has more than maxMistakeNum dist
        if (minMistakeNum > maxMistakeNum )
            return new ArrayList<>();

        // return most matched stops
        return sortedStops
                .stream()
                .filter( x -> x.getKey() == minMistakeNum )
                .map( x -> x.getValue() )
                .collect(Collectors.toList());
    }

    public Stop getNearestTramStop(Vec2d coord) {
        return getNearest(tramStops, coord);
    }

    public Stop getNearestTrollStop(Vec2d coord) {
        return getNearest(trollStops, coord);
    }

    public Stop getNearestToNearestTramStop(Vec2d coord) {
        return getNearestToNearest(tramStops, coord);
    }

    public Stop getNearestToNearestTrollStop(Vec2d coord) {
        return getNearestToNearest(trollStops, coord);
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

    public File getResourceFile(String fileName) {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    //

    private double distance(Stop stop1, Stop stop2 ) {
        if( stop1 == null || stop2 == null ) {
            return 0.0f;
        }
        return stop1.getCoord().distanceSq(stop2.getCoord());
    }

    private Stop getNearest(List<Stop> stops, Vec2d coord) {
        if (stops == null || stops.size() == 0) {
            System.out.println("No single stop given");
            return null;
        }

        Stop nearestStop = stops.get(0);
        for (Stop currentStop : stops) {
            if (coord.distanceSq(currentStop.getCoord()) < coord.distanceSq(nearestStop.getCoord())) {
                nearestStop = currentStop;
            }
        }

        return nearestStop;
    }

    private Stop getNearestToNearest(List<Stop> stops, Vec2d coord) {
        Stop target = getNearest(stops, coord);
        if( target == null ) {
            System.out.println("No single stop given");
            return null;
        }

        Stop nearestToNearest = null;

        // choose first not target
        for (Stop current : stops) {
            if (current.getId().equals(target.getId()) == false)
                nearestToNearest = current;
        }


        for (Stop current : stops) {
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
}
