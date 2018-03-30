package com.oybek.ekbts;

import com.oybek.ekbts.entities.TramStop;
import com.sun.javafx.geom.Vec2d;

public class Main
{
    public static void main(String[] args)
    {
        Engine engine = new Engine();
        Ettu ettu = new Ettu();

        TramStop tramStop = engine.getNearest( new Vec2d( 56.840719, 60.651862 ) );
        System.out.println( tramStop.toString() );

        System.out.println( ettu.getInfo( tramStop ) );
    }
}
