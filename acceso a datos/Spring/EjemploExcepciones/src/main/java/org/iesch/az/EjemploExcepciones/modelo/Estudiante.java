package org.iesch.az.EjemploExcepciones.modelo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private long id;
    private String nombre;
    private String apellidos;
    private String email;
    private  int edad;
    private String ciclo;//DAM DAW ETC
}
