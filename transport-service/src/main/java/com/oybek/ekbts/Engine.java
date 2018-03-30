package com.oybek.ekbts;

import com.google.gson.Gson;
import com.oybek.ekbts.entities.TramStop;
import com.sun.javafx.geom.Vec2d;
import org.jfree.graphics2d.svg.SVGGraphics2D;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Engine
{
    ArrayList<TramStop> tramStops;

    public Engine()
    {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(getResourceFile("json/tram-stops.json")))
        {
            // Convert JSON to Java Object
            TramStop[] tramStopsRaw = gson.fromJson(reader, TramStop[].class);
            tramStops = new ArrayList<>( Arrays.asList(tramStopsRaw) );
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public TramStop getNearest(Vec2d coord)
    {
        if( tramStops.size() == 0 )
        {
            System.out.println( "No single tram stop given" );
            return null;
        }

        TramStop nearestTramStop = tramStops.get(0);
        for( TramStop currentTramStop : tramStops )
        {
            if( coord.distanceSq( currentTramStop.getLatitude(), currentTramStop.getLongitude() )
                < coord.distanceSq( nearestTramStop.getLatitude(), nearestTramStop.getLongitude() ) )
            {
                nearestTramStop = currentTramStop;
            }
        }

        return nearestTramStop;
    }

    public File getResourceFile(String fileName)
    {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
