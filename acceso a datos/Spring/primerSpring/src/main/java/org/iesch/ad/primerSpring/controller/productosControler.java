package org.iesch.ad.primerSpring.controller;

import org.iesch.ad.primerSpring.modelo.Producto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class productosControler {
    @Value("${name}")
    String name;
    @PostMapping("/Producto")
    public void recibeproducto(@RequestBody Producto producto){
        System.out.println(producto.toString());
        System.out.println(name);
    }
    @GetMapping("/Producto")
    public Producto dameproducto(){
        Producto p = Producto.builder()
                .nombre(name).id(32).build();
        


        return p;
    }
}
