package org.iesch.ad.primerSpring.controller;

import org.iesch.ad.primerSpring.Service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class convertidorControler {

    @Autowired
    Utils utils;

    @GetMapping("/conDistancia/{km}")
    public Double convertidorDistancias(@PathVariable int km ){
        double millas = 0d;
        millas = utils.convierteamillas(km);
        return millas;
    }
    @GetMapping("/conTemp/{cels}")
    public double convertidortemperatura(@PathVariable int cels){
        double farenhight = 0d;
        farenhight = utils.convierteafarenhight(cels);
        return farenhight;
    }

}