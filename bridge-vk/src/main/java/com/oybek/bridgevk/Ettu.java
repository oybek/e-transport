package com.oybek.bridgevk;

import com.google.gson.Gson;
import com.oybek.bridgevk.Entities.Geo;
import com.oybek.bridgevk.Entities.StopInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Ettu {
    Gson gson = new Gson();

    private static final String urlGetNearestTramStops = "http://localhost:9100/tram_stops/get_nearest?latitude=%f&longitude=%f&n=%d";
    private static final String urlGetNearestTrollStops = "http://localhost:9100/troll_stops/get_nearest?latitude=%f&longitude=%f&n=%d";
    private static final String urlGetDistance = "http://localhost:9100/get_distance?lat1=%f&lon1=%f&lat2=%f&lon2=%f";

    //
    private List<StopInfo> get(String url, Geo geo, int n) {
        if (geo != null) {
            String response = Courier.get(String.format(url, geo.getLatitude(), geo.getLongitude(), n));
            StopInfo[] tramStopInfo = gson.fromJson(response, StopInfo[].class);
            return new ArrayList<>(Arrays.asList(tramStopInfo));
        } else {
            return null;
        }
    }

    public List<StopInfo> getNearestTramStops(Geo geo, int n) {
        return get(urlGetNearestTramStops, geo, n);
    }

    public List<StopInfo> getNearestTrollStops(Geo geo, int n) {
        return get(urlGetNearestTrollStops, geo, n);
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
