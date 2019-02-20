package com.oybek.etransport.controllers;

import com.oybek.etransport.entities.Stop;
import com.oybek.etransport.entities.User;
import com.oybek.etransport.services.StopService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StopController {

    private StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @RequestMapping("/test")
    public String test() {
        return "ok";
    }

    @RequestMapping("/bus/get")
    public List<Stop> getBuses(@RequestParam("name") String name) {
        return stopService.findBusStopsByName(name);
    }

    @RequestMapping("/tram/get")
    public List<Stop> getTrams(@RequestParam("name") String name,
                               @RequestParam(value = "user_app") String app,
                               @RequestParam(value = "user_id") String id) {
        return stopService.findTramStopsByName(name, new User(app, id));
    }

    @RequestMapping("/troll/get")
    public List<Stop> getTrolls(@RequestParam("name") String name) {
        return stopService.findTrollStopsByName(name);
    }
}
