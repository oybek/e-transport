package com.oybek.bridgevk;

import com.google.gson.Gson;
import com.oybek.bridgevk.Entities.Geo;
import com.oybek.bridgevk.Entities.StopInfo;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.net.Proxy.Type.HTTP;

@Component
public class Ettu {
    Gson gson = new Gson();

    private static final String urlGetDistance = "http://localhost:9100/get_distance?lat1=%f&lon1=%f&lat2=%f&lon2=%f";

    private static final String urlGetNearestTramStops = "http://localhost:9100/tram_stops/get_nearest?latitude=%f&longitude=%f&n=%d";
    private static final String urlGetNearestTrollStops = "http://localhost:9100/troll_stops/get_nearest?latitude=%f&longitude=%f&n=%d";

    private static final String urlTramStops = "http://localhost:9100/tram_stops/get?name=%s";
    private static final String urlTrollStops = "http://localhost:9100/troll_stops/get?name=%s";

    public List<StopInfo> getTrams(String name) {
        return get(urlTramStops, name);
    }

    public List<StopInfo> getNearestTrams(Geo geo, int n) {
        return get(urlGetNearestTramStops, geo, n);
    }

    public List<StopInfo> getTrolls(String name) {
        return get(urlTrollStops, name);
    }

    public List<StopInfo> getNearestTrolls(Geo geo, int n) {
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

    //
    private List<StopInfo> get(String url, String name) {
        if (name != null) {
            // url encode bot's response
            try {
                String response = Courier.get(
                        String.format(url, URLEncoder.encode(name, java.nio.charset.StandardCharsets.UTF_8.toString()))
                );
                StopInfo[] tramStopInfo = gson.fromJson(response, StopInfo[].class);
                return new ArrayList<>(Arrays.asList(tramStopInfo));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
