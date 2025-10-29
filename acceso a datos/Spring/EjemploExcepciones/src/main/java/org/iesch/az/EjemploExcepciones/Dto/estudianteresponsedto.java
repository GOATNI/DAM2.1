package org.iesch.az.EjemploExcepciones.Dto;

import lombok.*;
import org.iesch.az.EjemploExcepciones.modelo.Estudiante;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class estudianteresponsedto {
    private long id;
    private String nombre;
    private String apellidos;

    public estudianteresponsedto(Estudiante estudiante){
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellidos = estudiante.getApellidos();
    }
}
