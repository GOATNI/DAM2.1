package org.iesch.SegundoSpring.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Usuario {
    long id;
    String nombre;
    String apellidos;
    String direccion;
    String passwd;
}
