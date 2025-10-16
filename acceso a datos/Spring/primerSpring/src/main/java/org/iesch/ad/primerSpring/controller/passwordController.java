package org.iesch.ad.primerSpring.controller;

import org.iesch.ad.primerSpring.Service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class passwordController {
    @Autowired
    Utils utils;

    @GetMapping("/generaej1/{numeroCaracteres}")
    public String ej1(@PathVariable int numeroCaracteres){

        String passwd = utils.generaContrasenasAZ(numeroCaracteres);

        return passwd;
    }

    @GetMapping("/genera2")
    public String ej1_2(){
        int longitud = 9;

        String passwd = utils.generaContrasenasAZAlfanumericas(longitud);

        return passwd;
    }

}
