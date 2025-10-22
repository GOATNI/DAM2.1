package org.iesch.ad.EjemploJson.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Persona {
    private String nombre;
    private String apellido;
}
