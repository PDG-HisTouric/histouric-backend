package com.pdg.histouric.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class Prueba {

    @GetMapping
    public String prueba(){
        return "Llegue";
    }
}
