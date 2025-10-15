package org.iesch.ad.primerSpring.controller;

import org.iesch.ad.primerSpring.Service.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MiPrimerControler {

    @GetMapping("/saludo")
    public String saludo(){
        return "Hola Mundo";

    }
    @GetMapping("/saluda/{nombre}")
    public Map<String,String> saludarA(@PathVariable String nombre){
        Map<String,String> map = Map.of("Saludo","Hola"+nombre);
        return map;
    }
    @GetMapping("/buscar")
    public Map<String,String> Buscar(@RequestParam(required = true, name ="nombre") String nombre){
        Utils utils = new Utils();
        String nombremayus = utils.trasnformarAMayusculas(nombre);
        Map<String,String> map = Map.of("Saludo","Buscamos a "+nombremayus);
        return map;
    }


}
