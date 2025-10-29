package org.iesch.az.EjemploExcepciones.controladores;

import org.iesch.az.EjemploExcepciones.modelo.Estudiante;
import org.iesch.az.EjemploExcepciones.service.estudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")

public class estudiantecontrolador {

    @Autowired
    estudianteService EstudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenertodos (){
        List<Estudiante> Listaestudiantes = EstudianteService.obtenerTodos();

        return ResponseEntity.ok(Listaestudiantes);
    }


}
