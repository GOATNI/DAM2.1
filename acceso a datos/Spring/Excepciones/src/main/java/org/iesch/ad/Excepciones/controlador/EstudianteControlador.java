package org.iesch.ad.Excepciones.controlador;

import jakarta.validation.Valid;
import org.iesch.ad.Excepciones.Modelo.Estudiante;
import org.iesch.ad.Excepciones.Modelo.dto.EstudianteRequestestPeticiondto;
import org.iesch.ad.Excepciones.Modelo.dto.EstudianteResponseDTO;
import org.iesch.ad.Excepciones.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControlador {

    @Autowired
    EstudianteService estudianteService;

    @GetMapping
    // @CrossOrigin(origins = "http://localhost")
    public ResponseEntity<List<EstudianteResponseDTO>> obtenerTodos () {

        List<EstudianteResponseDTO> studiantes = estudianteService.obtenerTodos();

        return ResponseEntity.ok(studiantes);
    }
    /*
    * Get/api/estudiantes/id
    * obrtener un estudiante por id y puede lanzar excepciones estudiante no encontrado
    *
    * */
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> obtenerUno (@PathVariable Long id) {
        EstudianteResponseDTO estudianteResponseDTO = estudianteService.obtenerPorId (id);
            return ResponseEntity.ok(estudianteResponseDTO);
    }
    /*
     * post/api/estudiantes/
     * creo un estudiante nuevo
     *  puede lanzar excepciones emailduplicationexception
     *
     * */

    @PostMapping
    public ResponseEntity<EstudianteResponseDTO> crearUsuario (@Valid @RequestBody EstudianteRequestestPeticiondto estudianterequestdto){
        EstudianteResponseDTO nuevoEstudiante = estudianteService.crear(estudianterequestdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);


    }
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> actualizar(
            @PathVariable Long id,@Valid @RequestBody EstudianteRequestestPeticiondto estudianterequestdto
    ){
        EstudianteResponseDTO nuevoActualizado = estudianteService.actualizar(id,estudianterequestdto);
        return ResponseEntity.ok(nuevoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    /**
     * obtener estudiantes por ciclo formativo
     * puede lanzar excepciones datos  invalidos exception o estudiante no encontrado exceptio
     */
    @GetMapping("/ciclo/{ciclo}")
    public ResponseEntity<List<EstudianteResponseDTO>> obtenerporciclo(@PathVariable String ciclo)
    {

        List<EstudianteResponseDTO> list = estudianteService.obtenerPorCiclo(ciclo);
        return ResponseEntity.ok(list);
    }


}
