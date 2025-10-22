package org.iesch.SegundoSpring.modelo;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Product {
    Long id;
    String name;
    double price;
    String descripcion;
    String categoria;
    int stock;
}
