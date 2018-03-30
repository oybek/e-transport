package com.oybek.ekbts;

import com.oybek.ekbts.entities.TramStop;
import com.sun.javafx.geom.Vec2d;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        Engine engine = new Engine();

        TramStop tramStop = engine.getNearest( new Vec2d( 56.844202, 60.499352 ) );
        System.out.println( tramStop.toString() );

        // JSoup Example 2 - Reading HTML page from URL
        Document doc;
        try {
            doc = Jsoup.connect("http://m.ettu.ru/station/3408").get();
            System.out.println( doc.select("p").first().text() );
            for( Element div : doc.select( "div" ) )
            {
                if(div.children().size() == 3)
                {
                    String msg = "";
                    for( Element child : div.children() )
                        msg += child.text() + ", ";
                    System.out.println(msg + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
