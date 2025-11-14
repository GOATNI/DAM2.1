package com.example.EJE1.config;

import com.example.EJE1.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Configuracion {



    @Bean
    public Map<Long,Producto> productosMap(){

        Producto p1 = new Producto(
                1L,
                "Auriculares Inalámbricos Pro",
                "59.99",
                "Auriculares Bluetooth con cancelación pasiva de ruido.",
                "Electrónica",
                "120"
        );

        Producto p2 = new Producto(
                2L,
                "Botella Térmica 750ml",
                "18.50",
                "Mantiene bebidas frías por 24h y calientes por 12h.",
                "Hogar",
                "80"
        );

        Producto p3 = new Producto(
                3L,
                "Teclado Mecánico RGB",
                "79.00",
                "Teclado mecánico con switches rojos silenciosos.",
                "Computación",
                "45"
        );

        Producto p4 = new Producto(
                4L,
                "Mochila Antirrobo",
                "35.99",
                "Mochila con cierre oculto y puerto USB.",
                "Accesorios",
                "100"
        );

        Producto p5 = new Producto(
                5L,
                "Cafetera Automática Compacta",
                "129.90",
                "Cafetera de presión programable con espumador.",
                "Cocina",
                "30"
        );
        Map<Long,Producto> productoMap = new HashMap<>();
        productoMap.put(p1.getId(), p1);
        productoMap.put(p2.getId(), p2);
        productoMap.put(p3.getId(), p3);
        productoMap.put(p4.getId(), p4);
        productoMap.put(p5.getId(), p5);


        return productoMap;


    }
}
