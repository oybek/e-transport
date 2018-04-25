package com.oybek.bridgevk;

import com.google.gson.Gson;
import com.oybek.bridgevk.Entities.Geo;
import com.oybek.bridgevk.Entities.TramStopInfo;
import org.springframework.stereotype.Component;

@Component
public class Ettu {
    Gson gson;

    private final String urlGetNearest = "http://localhost:8888/get_nearest?latitude=%f&longitude=%f";
    private final String urlGetNearestToNearest = "http://localhost:8888/get_nearest_to_nearest?latitude=%f&longitude=%f";
    private final String urlGetDistance = "http://localhost:8888/get_distance?lat1=%f&lon1=%f&lat2=%f&lon2=%f";


    Ettu() {
        gson = new Gson();
    }

    // returns information about nearest tramStop to getPos
    public TramStopInfo getNearest(Geo geo) {
        if (geo != null) {
            String response = Courier.get(String.format(urlGetNearest, geo.getLatitude(), geo.getLongitude()));
            TramStopInfo tramStopInfo = gson.fromJson(response, TramStopInfo.class);
            return tramStopInfo;
        } else {
            return null;
        }
    }

    // returns information about nearest tramStop to getPos
    public TramStopInfo getNearestToNearest(Geo geo) {
        if (geo != null) {
            String response = Courier.get(String.format(urlGetNearestToNearest, geo.getLatitude(), geo.getLongitude()));
            TramStopInfo tramStopInfo = gson.fromJson(response, TramStopInfo.class);
            return tramStopInfo;
        } else {
            return null;
        }
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
