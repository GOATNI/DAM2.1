package com.example.EJE1.modelo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    Long id;
    String name;
    String price;
    String descripcion;
    String category;
    String stock;

}
