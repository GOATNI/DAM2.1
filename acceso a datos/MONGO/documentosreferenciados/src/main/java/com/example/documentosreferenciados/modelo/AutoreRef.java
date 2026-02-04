package com.example.documentosreferenciados.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "autores")
public class AutoreRef {
    @Id
    private String id;
    private String nombre;
    private String nacionalidad;

    public AutoreRef(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
}
