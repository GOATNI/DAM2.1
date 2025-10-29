package org.iesch.az.EjemploExcepciones.config;

import org.iesch.az.EjemploExcepciones.modelo.Estudiante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class configuracion {

    @Bean
    public Map<Long, Estudiante> inicializa(){
        Map<Long,Estudiante> estudianteMap = new HashMap<>();
        Estudiante estudiante = new Estudiante(1,"Juan","Garcia Lopez","juan@iesch.oeg",20,"Dam");

        Estudiante estudiante2 = new Estudiante(2,"Maria","Rodrigez Sanz","maria@iesch.oeg",19,"Daw");

        Estudiante estudiante3 = new Estudiante(3,"Pedro","Lopez","pedro@iesch.oeg",21,"Dam");

        estudianteMap.put(estudiante.getId(), estudiante);
        estudianteMap.put(estudiante2.getId(), estudiante2);
        estudianteMap.put(estudiante3.getId(), estudiante3);
        return estudianteMap;
    }
}
