package com.oybek.bridgevk;

import com.google.gson.Gson;
import com.oybek.bridgevk.Entities.Geo;
import com.oybek.bridgevk.Entities.StopInfo;
import org.springframework.stereotype.Component;

@Component
public class Ettu {
    Gson gson;

    private final String urlGetNearestTramStop = "http://localhost:9100/tram_stops/get_nearest?latitude=%f&longitude=%f";
    private final String urlGetNearestToNearestTramStop = "http://localhost:9100/tram_stops/get_nearest_to_nearest?latitude=%f&longitude=%f";

    private final String urlGetNearestTrollStop = "http://localhost:9100/troll_stops/get_nearest?latitude=%f&longitude=%f";
    private final String urlGetNearestToNearestTrollStop = "http://localhost:9100/troll_stops/get_nearest_to_nearest?latitude=%f&longitude=%f";

    private final String urlGetDistance = "http://localhost:9100/get_distance?lat1=%f&lon1=%f&lat2=%f&lon2=%f";


    Ettu() {
        gson = new Gson();
    }

    //
    private StopInfo get(String url, Geo geo) {
        if (geo != null) {
            String response = Courier.get(String.format(url, geo.getLatitude(), geo.getLongitude()));
            StopInfo tramStopInfo = gson.fromJson(response, StopInfo.class);
            return tramStopInfo;
        } else {
            return null;
        }
    }

    public StopInfo getNearestTramStop(Geo geo) {
        return get(urlGetNearestTramStop, geo);
    }

    public StopInfo getNearestToNearestTramStop(Geo geo) {
        return get(urlGetNearestToNearestTramStop, geo);
    }

    public StopInfo getNearestTrollStop(Geo geo) {
        return get(urlGetNearestTrollStop, geo);
    }

    public StopInfo getNearestToNearestTrollStop(Geo geo) {
        return get(urlGetNearestToNearestTrollStop, geo);
    }

    //
    public Double getDistance(Geo geo1, Geo geo2) {
        if (geo1 != null && geo2 != null) {
            String requestResult = Courier.get(String.format(urlGetDistance
                    , geo1.getLatitude()
                    , geo1.getLongitude()
                    , geo2.getLatitude()
                    , geo2.getLongitude()));

            return Double.parseDouble(requestResult);
        } else {
            return null;
        }
    }
}
