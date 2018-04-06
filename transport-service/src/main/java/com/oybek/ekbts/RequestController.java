package com.oybek.ekbts;

import com.oybek.ekbts.entities.Result;
import com.oybek.ekbts.entities.TramStop;
import com.sun.javafx.geom.Vec2d;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    private Ettu ettu;
    private Engine engine;

    public RequestController(Ettu ettu, Engine engine) {
        this.ettu = ettu;
        this.engine = engine;
    }

    @GetMapping(value = "/test")
    public String getInfo() {
        return "ok";
    }

    @GetMapping(value = "/get")
    public Result get(@RequestParam("latitude") double latitude
            , @RequestParam("longitude") double longitude ) {
        TramStop tramStop = engine.getNearest( new Vec2d( latitude, longitude ) );
        return ettu.getInfo(tramStop);
    }
}
