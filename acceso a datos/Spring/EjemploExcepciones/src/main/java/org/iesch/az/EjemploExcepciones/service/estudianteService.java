package org.iesch.az.EjemploExcepciones.service;

import org.iesch.az.EjemploExcepciones.modelo.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class estudianteService {
    @Autowired
    Map<Long, Estudiante> estudianteMap;
    public List<Estudiante> obtenerTodos(){
        List<Estudiante> lisaEstudiantes = new ArrayList<>(estudianteMap.values());
        return lisaEstudiantes;
    }
}
