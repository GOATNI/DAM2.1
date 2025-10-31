package org.iesch.az.EjemploExcepciones.service;

import org.iesch.az.EjemploExcepciones.Dto.estudianteresponsedto;
import org.iesch.az.EjemploExcepciones.exceptions.ResourceNotFoundException;
import org.iesch.az.EjemploExcepciones.modelo.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class estudianteService {
    private final Map<Long, Estudiante> estudianteMap;

    @Autowired
    public estudianteService(Map<Long, Estudiante> estudianteMap) {
        this.estudianteMap = estudianteMap;
    }

    // Preserve original method returning domain objects
    public List<Estudiante> obtenerTodos(){
        List<Estudiante> listaEstudiantes = new ArrayList<>(estudianteMap.values());
        return listaEstudiantes;
    }

    // New: return DTOs
    public List<estudianteresponsedto> obtenerTodosDTO(){
        return estudianteMap.values()
                .stream()
                .map(est -> new estudianteresponsedto(est))
                .collect(Collectors.toList());
    }

    // New: find by id and return DTO, throw if not found
    public estudianteresponsedto obtenerPorIdDTO(Long id){
        Estudiante estudiante = estudianteMap.get(id);
        if (estudiante == null){
            throw new ResourceNotFoundException("Estudiante not found with id: " + id);
        }
        return new estudianteresponsedto(estudiante);
    }
}
