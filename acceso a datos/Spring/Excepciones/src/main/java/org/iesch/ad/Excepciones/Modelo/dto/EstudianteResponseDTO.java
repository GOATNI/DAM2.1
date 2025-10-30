package org.iesch.ad.Excepciones.Modelo.dto;

import lombok.*;
import org.iesch.ad.Excepciones.Modelo.Estudiante;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private String apellidos;

    public  EstudianteResponseDTO (Estudiante estudiante){
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellidos = estudiante.getApellidos();
    }
}
