package io.github.oybek.services.impl;

import com.sun.javafx.geom.Vec2d;
import io.github.oybek.entities.Stop;
import io.github.oybek.repositories.StopRepository;
import io.github.oybek.services.StopService;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class StopServiceImpl implements StopService {

    private final int updateTime = 30000;

    private StopRepository stopRepository;
    private Algorithm algorithm;
    private Ettu ettu;

    private List<Stop> stops;

    StopServiceImpl(StopRepository stopRepository, Algorithm algorithm, Ettu ettu) {
        this.stopRepository = stopRepository;
        this.algorithm = algorithm;
        this.ettu = ettu;
    }

    @PostConstruct
    @Transactional
    public void init() throws Exception {
        this.stops = getListFromIterator(stopRepository.findAll().iterator());

        PrintWriter out = new PrintWriter("/tmp/inserts.sql");
        for (Stop stop : stops) {
            String output =
                    "insert into stop values (" +
                    "'" + stop.getId() + "', " +
                    "'" + String.join("|", stop.getNames()) + "', " +
                    "'" + stop.getDirection() + "', " +
                    + stop.getLatitude() + ", " +
                    + stop.getLongitude() + ", " +
                    "'" + stop.getType() + "');";
            out.println(output);
        }
        out.flush();
        System.out.println("done");
    }

    private static <T> List<T> getListFromIterator(Iterator<T> iterator) {
        // Create an empty list
        List<T> list = new ArrayList<>();

        // Add each element of iterator to the List
        iterator.forEachRemaining(list::add);

        // Return the List
        return list;
    }

    private List<Stop> findStopsByNameFromList(String name, List<Stop> stops) {
        final int maxDist = name.length()/4;

        // get stops sorted by levenshtein dist to 'name'
        final List<Pair<Integer, Stop>> sortedStops = stops
                .stream()
                .map(stop -> new Pair<>(algorithm.calcDist(stop.getNames(), name), stop))
                .sorted(Comparator.comparing(Pair<Integer, Stop>::getKey))
                .collect(Collectors.toList());

        // get minimal levenshtein dist
        final Optional<Integer> minDistOpt = sortedStops
                .stream()
                .findFirst()
                .map(Pair::getKey);

        // get first most 4 stops which has min levenshtein distance
        return minDistOpt.map(minDist ->
                minDist > maxDist
                        ? new ArrayList<Stop>()
                        : sortedStops
                            .stream()
                            .filter(x -> x.getKey().equals(minDist))
                            .map(Pair::getValue)
                            .limit(4)
                            .collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    private Stop findNearestStop(Vec2d coord, List<Stop> stops) {
        // get stops sorted by levenshtein dist to 'name'
        final List<Pair<Double, Stop>> sortedStops = stops
                .stream()
                .map(stop -> new Pair<>(coord.distanceSq(stop.getLatitude(), stop.getLongitude()), stop))
                .sorted(Comparator.comparing(Pair<Double, Stop>::getKey))
                .collect(Collectors.toList());
        return sortedStops.get(0).getValue();
    }

    private List<Stop> updateReaches(List<Stop> stops) {
        return stops
                .stream()
                .peek(stop -> {
                    final long currentTime = System.currentTimeMillis();
                    if (stop.getUpdated() == null || currentTime - stop.getUpdated().getTime() > updateTime) {
                        stop.setReaches(ettu.getReaches(stop));
                        stop.setUpdated(new Timestamp(currentTime));
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Stop> findStopsByName(String name, String type) {
        final List<Stop> stops = this.stops.stream().filter(s -> s.getType().equals(type)).collect(Collectors.toList());
        List<Stop> selectedStops = findStopsByNameFromList(name, stops);
        return updateReaches(selectedStops);
    }

    private List<Stop> getJustStops(String name, String type) {
        final List<Stop> stops = this.stops.stream().filter(s -> s.getType().equals(type)).collect(Collectors.toList());
        return findStopsByNameFromList(name, stops);
    }

    @Override
    public Boolean isBusStop(String name) {
        return getJustStops(name, "bus").size() > 0;
    }

    @Override
    public Boolean isTramStop(String name) {
        return getJustStops(name, "tram").size() > 0;
    }

    @Override
    public Boolean isTrollStop(String name) {
        return getJustStops(name, "troll").size() > 0;
    }

    @Override
    public List<Stop> findBusStopsByName(String name) {
        return findStopsByName(name, "bus");
    }

    @Override
    public List<Stop> findTramStopsByName(String name) {
        return findStopsByName(name, "tram");
    }

    @Override
    public List<Stop> findTrollStopsByName(String name) {
        return findStopsByName(name, "troll");
    }

    @Override
    public Stop findNearestBusStops(Vec2d pos) {
        final List<Stop> busStops = this.stops.stream().filter(s -> s.getType().equals("bus")).collect(Collectors.toList());
        return findNearestStop(pos, busStops);
    }

    @Override
    public Stop findNearestTramStops(Vec2d pos) {
        final List<Stop> tramStops = this.stops.stream().filter(s -> s.getType().equals("tram")).collect(Collectors.toList());
        return findNearestStop(pos, tramStops);
    }

    @Override
    public Stop findNearestTrollStops(Vec2d pos) {
        final List<Stop> trollStops = this.stops.stream().filter(s -> s.getType().equals("troll")).collect(Collectors.toList());
        return findNearestStop(pos, trollStops);
    }
}
