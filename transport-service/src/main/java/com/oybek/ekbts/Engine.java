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
import java.util.stream.Stream;

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
                .replaceAll("трц", "")
                .replaceAll( "[^0-9а-я]", "" )
        ;
    }

    private List<Stop> getByName(List<Stop> stops, String name) {
        final String namePrepared = prepare(name);
        final int maxMistakeNum = name.length() / 3;

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
                .limit( 4 )
                .collect(Collectors.toList());
    }

    private File getResourceFile(String fileName) {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    private Stream<Stop> getNearest(List<Stop> stops, Vec2d userPos, int n) {
        if (stops == null) {
            System.out.println("ERROR: No information about stops loaded");
            stops = new ArrayList<>();
        }

        return stops
                .stream()
                .map( stop -> new Pair<>(userPos.distanceSq(stop.getCoord()), stop) ) // calc distance from each stop to userPos wrap into Pair
                .sorted( Comparator.comparing(Pair<Double, Stop>::getKey) ) // sort stops by distance to userPos (nearest first)
                .map( x -> x.getValue() ) // extract stops from pairs
                .limit( n );
    }

    //

    public List<Stop> getTramStopByName(String name) {
        return getByName(tramStops, name);
    }

    public List<Stop> getTrollStopByName(String name) {
        return getByName(trollStops, name);
    }

    public Stream<Stop> getNearestTramStops(Vec2d coord, int n) {
        return getNearest(tramStops, coord, n);
    }

    public Stream<Stop> getNearestTrollStops(Vec2d coord, int n) {
        return getNearest(trollStops, coord, n);
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

}
