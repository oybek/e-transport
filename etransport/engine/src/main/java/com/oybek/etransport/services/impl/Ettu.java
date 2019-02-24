package com.oybek.etransport.services.impl;

import com.oybek.etransport.entities.Reach;
import com.oybek.etransport.entities.Stop;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

@Component
public class Ettu {

    public List<Reach> getReaches(Stop stop) {
        if (stop.getType().equals("tram") || stop.getType().equals("troll")) {
            return getEttuInfo(stop);
        } if (stop.getType().equals("bus")) {
            return getBustimeInfo(stop);
        } else {
            return new ArrayList<>();
        }
    }

    private List<Reach> getBustimeInfo(Stop stop) {
        List<Reach> reaches = new ArrayList<>();
        final String baseUrl = "https://www.bustime.ru/ekaterinburg/stop/id/";
        try {
            final Document doc = Jsoup.connect(baseUrl + stop.getId()).get();

            doc.select("tbody").stream().findFirst().map(tbody -> {
                tbody.select("tr").forEach(row -> {

                    Optional<String> reachTimeStrOpt = row
                            .select("td")
                            .stream()
                            .map(x -> x.select("b")
                                    .stream()
                                    .findFirst().map(Element::ownText))
                            .findFirst().orElse(Optional.empty());

                    if (reachTimeStrOpt.isPresent()) {
                        String[] hhmm = reachTimeStrOpt.get().split(":");
                        if (hhmm.length == 2) {
                            LocalTime reachTime = LocalTime.of(Integer.parseInt(hhmm[0]), Integer.parseInt(hhmm[1]));

                            Element element = row.select("td").last();
                            if (element != null) {
                                element.select("div").forEach(x -> {
                                    String route = x.ownText();
                                    if (route.charAt(0) != 'Т') {
                                        Reach reach = new Reach();

                                        reach.setStop(stop);
                                        reach.setRoute(route.replace("+", "").trim());
                                        reach.setTime(Math.max(LocalTime.now().until(reachTime, MINUTES), 0));

                                        reaches.add(reach);
                                    }
                                });
                            }
                        }
                    }
                });
                return null;
            });
            return reaches;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Reach> getEttuInfo(Stop stop) {
        final String baseUrl = "http://m.ettu.ru/station/";

        try {
            final Document doc = Jsoup.connect(baseUrl + stop.getId()).get();
            return doc
                    .select("div")
                    .stream()
                    .filter(div -> div.children().size() == 3)
                    .map(div -> {
                        Reach reach = new Reach();
                        reach.setStop(stop);
                        reach.setRoute(div.children().get(0).text());
                        reach.setTime(Long.parseLong(div.children().get(1).text().replaceAll("[^\\d.]", "").trim()));
                        return reach;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

