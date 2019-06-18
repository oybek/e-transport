package io.github.oybek.services.impl;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec2f;
import io.github.oybek.entities.Stop;
import io.github.oybek.repositories.ReachRepository;
import io.github.oybek.repositories.StopRepository;
import io.github.oybek.services.StopService;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StopServiceImpl implements StopService {

    private final int updateTime = 30000;

    private ReachRepository reachRepository;
    private StopRepository stopRepository;
    private Algorithm algorithm;
    private Ettu ettu;

    StopServiceImpl(ReachRepository reachRepository, StopRepository stopRepository, Algorithm algorithm, Ettu ettu) {
        this.reachRepository = reachRepository;
        this.stopRepository = stopRepository;
        this.algorithm = algorithm;
        this.ettu = ettu;
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
                    if (stop.getUpdated() == null || System.currentTimeMillis() - stop.getUpdated().getTime() > updateTime) {
                        reachRepository.deleteByStop(stop);
                        stop.setReaches(ettu.getReaches(stop));
                        stop.setUpdated(new Timestamp(System.currentTimeMillis()));
                        stopRepository.save(stop);
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Stop> findStopsByName(String name, String type) {
        List<Stop> stops = findStopsByNameFromList(name, stopRepository.findAllByType(type));
        return updateReaches(stops);
    }

    private List<Stop> getJustStops(String name, String type) {
        return findStopsByNameFromList(name, stopRepository.findAllByType(type));
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
    @Transactional
    public List<Stop> findBusStopsByName(String name) {
        return findStopsByName(name, "bus");
    }

    @Override
    @Transactional
    public List<Stop> findTramStopsByName(String name) {
        return findStopsByName(name, "tram");
    }

    @Override
    @Transactional
    public List<Stop> findTrollStopsByName(String name) {
        return findStopsByName(name, "troll");
    }

    @Override
    public Stop findNearestBusStops(Vec2d pos) {
        return findNearestStop(pos, stopRepository.findAllByType("bus"));
    }

    @Override
    public Stop findNearestTramStops(Vec2d pos) {
        return findNearestStop(pos, stopRepository.findAllByType("tram"));
    }

    @Override
    public Stop findNearestTrollStops(Vec2d pos) {
        return findNearestStop(pos, stopRepository.findAllByType("troll"));
    }
}
