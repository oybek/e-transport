package com.oybek.ekbts;

import com.google.gson.Gson;
import com.oybek.ekbts.entities.TramStop;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Gson gson = new Gson();

        Main obj = new Main();

        try (Reader reader = new FileReader(obj.getResourceFile("json/tram-stops.json")))
        {
			// Convert JSON to Java Object
            TramStop[] tramStopsRaw = gson.fromJson(reader, TramStop[].class);

            ArrayList<TramStop> tramStops = new ArrayList<>( Arrays.asList(tramStopsRaw) );

            System.out.println(tramStops.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getResourceFile( String fileName )
    {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
