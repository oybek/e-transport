package com.oybek.etransport.services.impl;

import com.oybek.etransport.entities.Request;
import com.oybek.etransport.entities.Stop;
import com.oybek.etransport.entities.User;
import com.oybek.etransport.repositories.ReachRepository;
import com.oybek.etransport.repositories.RequestRepository;
import com.oybek.etransport.repositories.StopRepository;
import com.oybek.etransport.services.StopService;
import com.sun.javafx.geom.Vec2f;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StopServiceImpl implements StopService {

    private final int updateTime = 30000;

    private RequestRepository requestRepository;
    private ReachRepository reachRepository;
    private StopRepository stopRepository;
    private Algorithm algorithm;
    private Ettu ettu;

    StopServiceImpl(RequestRepository requestRepository, ReachRepository reachRepository, StopRepository stopRepository, Algorithm algorithm, Ettu ettu) {
        this.requestRepository = requestRepository;
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
    public Optional<String> findNearestBusStops(Vec2f pos) {
        return Optional.empty();
    }

    @Override
    public Optional<String> findNearestTramStops(Vec2f pos) {
        return Optional.empty();
    }

    @Override
    public Optional<String> findNearestTrollStops(Vec2f pos) {
        return Optional.empty();
    }
}
