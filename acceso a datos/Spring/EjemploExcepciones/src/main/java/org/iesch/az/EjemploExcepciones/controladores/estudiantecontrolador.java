package org.iesch.az.EjemploExcepciones.controladores;

import org.iesch.az.EjemploExcepciones.Dto.estudianteresponsedto;
import org.iesch.az.EjemploExcepciones.service.estudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class estudiantecontrolador {

    private final estudianteService estudianteService;

    public estudiantecontrolador(estudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<estudianteresponsedto>> obtenertodos (){
        List<estudianteresponsedto> lista = estudianteService.obtenerTodosDTO();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<estudianteresponsedto> obtenerPorId(@PathVariable Long id){
        estudianteresponsedto dto = estudianteService.obtenerPorIdDTO(id);
        return ResponseEntity.ok(dto);
    }

}
