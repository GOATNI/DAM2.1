package org.iesch.SegundoSpring.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UsuarioDTORespuesta {
    String nombre;
    String passwd;
}
