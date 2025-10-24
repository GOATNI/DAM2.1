package org.iesch.SegundoSpring.config;

import org.iesch.SegundoSpring.modelo.Product;
import org.iesch.SegundoSpring.modelo.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.multipart.MultipartWriterSupport;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Configuracion {

    @Bean
    public Map<Long,Product> inicializa(){
        Map<Long,Product> products = new HashMap<>();
        products.put(1L,Product.builder().id(1L).name("Fairy").categoria("Limpieza").price(5.25).descripcion("lavavajillas a mana").stock(16).build());
        products.put(2L,Product.builder().id(2L).name("Colacao").categoria("Comida").price(6.25).descripcion("Bebida de coca saluble").stock(16).build());
        products.put(3L,Product.builder().id(3L).name("Lejia").categoria("Limpieza").price(9).descripcion("limpieza").stock(6).build());
        products.put(4L,Product.builder().id(4L).name("Pan").categoria("Comida").price(1.20).descripcion("pan").stock(50).build());
        return products;
    }
    @Bean
    public Map<Long, Usuario> initUser(){
        Map<Long,Usuario> users = new HashMap<>();
        users.put(1L,Usuario.builder().id(1L).nombre("Azeem").apellidos("Sultana").passwd("abcdefg").direccion("calle meson de jativa").build());
        return users;
    }

}
