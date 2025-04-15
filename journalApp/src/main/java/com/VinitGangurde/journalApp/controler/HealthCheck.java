package com.VinitGangurde.journalApp.controler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public String healthcheck(){

        return "ok madhi aahe sagale";
    }


}
