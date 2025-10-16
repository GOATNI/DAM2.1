package org.iesch.ad.primerSpring.modelo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Data
@Builder
public class Producto {
    int id;
    String nombre;
    float precio;
}
