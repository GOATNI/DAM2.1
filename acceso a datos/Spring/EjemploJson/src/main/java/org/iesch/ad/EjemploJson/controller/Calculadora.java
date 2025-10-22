package org.iesch.ad.EjemploJson.controller;

import org.iesch.ad.EjemploJson.modelo.Numeros;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculadora {

    @GetMapping("/suma")
    public String suma(@RequestBody Numeros numeros){
        return  "El resultado de la suma es: " + (numeros.getOperando1() + numeros.getOperando2());
    }
    @GetMapping("/resta")
    public String resta(@RequestBody Numeros numeros){
        return  "El resultado de la resta es: " + (numeros.getOperando1() - numeros.getOperando2());
    }
    @GetMapping("/multiplicacion")
    public String multiplicacion(@RequestBody Numeros numeros){
        return  "El resultado de la multiplicacion es: " + (numeros.getOperando1() * numeros.getOperando2());
    }
    @GetMapping("/division")
    public String division(@RequestBody Numeros numeros){
        return  "El resultado de la division es: " + (numeros.getOperando1() / numeros.getOperando2());
    }
}
