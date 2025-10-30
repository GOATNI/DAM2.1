package org.iesch.ad.Excepciones.service;

import jakarta.validation.Valid;
import org.iesch.ad.Excepciones.Modelo.Estudiante;
import org.iesch.ad.Excepciones.Modelo.dto.EstudianteRequestestPeticiondto;
import org.iesch.ad.Excepciones.Modelo.dto.EstudianteResponseDTO;
import org.iesch.ad.Excepciones.exception.DatosInvalidosException;
import org.iesch.ad.Excepciones.exception.EmailDuplicadoexception;
import org.iesch.ad.Excepciones.exception.EstudianteNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {

    @Autowired
    Map<Long, Estudiante> estudiantes;

    public List<EstudianteResponseDTO> obtenerTodos() {
        List<Estudiante> listaEstudiantes = new ArrayList<>(estudiantes.values());
        List<EstudianteResponseDTO> estudianteResponseDTOS =
                listaEstudiantes.stream().map(EstudianteResponseDTO::new).toList();



        return estudianteResponseDTOS;
    }

    public EstudianteResponseDTO obtenerPorId(Long id) {
        Estudiante estudiante = estudiantes.get(id);
        if(estudiante != null){
            return new EstudianteResponseDTO(estudiante);
        } else {
            throw new EstudianteNoEncontradoException(id);
        }
    }

    public EstudianteResponseDTO crear(@Valid EstudianteRequestestPeticiondto estudianterequestdto) {
        //validamos que exista en base de datos el gmail
        boolean emailExiste = estudiantes.values().stream().anyMatch(estudiante ->
                estudianterequestdto.getEmail().equalsIgnoreCase(estudiante.getEmail()));
        if (emailExiste) {
            throw new EmailDuplicadoexception(estudianterequestdto.getEmail());
        }

        //crear usuario en mi mapa;
        Long maxkey = estudiantes.keySet().stream().max(Long::compareTo).orElse(1L);
        Estudiante estudiante = new Estudiante(maxkey+1,estudianterequestdto.getNombre(), estudianterequestdto.getApellidos(),
                estudianterequestdto.getEmail(), estudianterequestdto.getEdad(), estudianterequestdto.getCiclo());
        estudiantes.put(estudiante.getId(),estudiante);

        return new EstudianteResponseDTO(estudiante);
    }

    public EstudianteResponseDTO actualizar(Long id, @Valid EstudianteRequestestPeticiondto estudianterequestdto) {
        Estudiante estudiante = estudiantes.get(id);
        if(estudiante == null){
            throw new EstudianteNoEncontradoException(id);
        }
        //Validar el gmail
        boolean emailExiste = estudiantes.values().stream().anyMatch(estudiantes ->
                estudiantes.getEmail().equalsIgnoreCase(estudiante.getEmail()));
        if (emailExiste) {
            throw new EmailDuplicadoexception(estudianterequestdto.getEmail());
        }
        estudiante.setNombre(estudianterequestdto.getNombre());
        estudiante.setApellidos(estudianterequestdto.getApellidos());
        estudiante.setEmail(estudianterequestdto.getEmail());
        estudiante.setEdad(estudianterequestdto.getEdad());
        estudiante.setCiclo(estudianterequestdto.getCiclo());
        return new EstudianteResponseDTO(estudiante);
    }

    public void eliminar(Long id) {
        Estudiante estudiante = estudiantes.get(id);
        if (estudiante == null){
            throw new EstudianteNoEncontradoException(id);
        }
        estudiantes.remove(id);

    }

    public List<EstudianteResponseDTO> obtenerPorCiclo(String ciclo) {
        //validamos que el ciclo sea valido
        if (!ciclo.matches("(?i)DAM|DAW|ASIR|SMR")){
            throw new DatosInvalidosException("Ciclo no valido");
        }
        List<EstudianteResponseDTO> resultado = estudiantes.values().stream().filter
                (estudiante -> estudiante.getCiclo() .equalsIgnoreCase(ciclo)).map(EstudianteResponseDTO::new).toList();
        if (resultado.isEmpty()){
            throw new EstudianteNoEncontradoException(ciclo);
        }
        return resultado;
    }
}
