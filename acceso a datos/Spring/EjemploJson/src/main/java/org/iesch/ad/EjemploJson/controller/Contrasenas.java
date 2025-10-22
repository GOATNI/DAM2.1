package org.iesch.ad.EjemploJson.controller;

import org.iesch.ad.EjemploJson.modelo.DatosPasswd;
import org.iesch.ad.EjemploJson.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contrasenas {
    @Autowired
    Utils utils;
    @PostMapping("/generaPasswd")
    public String letra(@RequestBody DatosPasswd datos){
        return utils.generaContraseñasAZ(datos.getLongitud());
    }

}
