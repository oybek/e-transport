package com.oybek.ekbts;

import com.oybek.ekbts.entities.Result;
import com.oybek.ekbts.entities.TramInfo;
import com.oybek.ekbts.entities.TramStop;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Ettu {
    private static final String ETTU_ENDPOINT = "http://m.ettu.ru/station/";

    public Result getInfo(TramStop tramStop) {
        // JSoup Example 2 - Reading HTML page from URL
        Document doc;
        try {
            String id = tramStop.getId();
            if (id != null) {
                doc = Jsoup.connect(ETTU_ENDPOINT + id).get();

                List<TramInfo> tramInfos = new ArrayList<>();

                for (Element div : doc.select("div")) {
                    if (div.children().size() == 3) {
                        TramInfo tramInfo = new TramInfo();

                        tramInfo.setRoute(div.children().get(0).text());
                        tramInfo.setTimeReach(div.children().get(1).text().replaceAll("[^\\d.]", "").trim());
                        tramInfo.setDistanceReach(div.children().get(2).text().replaceAll("[^\\d.]", "").trim());

                        tramInfos.add(tramInfo);
                    }
                }

                Result result = new Result(tramInfos);
                result.setLatitude(tramStop.getLatitude());
                result.setLongitude(tramStop.getLongitude());
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(null);
    }
}
