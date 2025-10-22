package org.iesch.ad.EjemploJson.controller;

import org.iesch.ad.EjemploJson.modelo.Persona;
import org.iesch.ad.EjemploJson.service.GuardaDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NombreController {
    @Autowired
    GuardaDatos guardaDatos;
    //Reciba el json y lo procese y lo guarde en un fichero
    @PostMapping("/persona")
    public String guarda(@RequestBody Persona persona){
        System.out.println(persona);
        guardaDatos.guarda(persona);
        return guardaDatos.guarda(persona);
    }
}
