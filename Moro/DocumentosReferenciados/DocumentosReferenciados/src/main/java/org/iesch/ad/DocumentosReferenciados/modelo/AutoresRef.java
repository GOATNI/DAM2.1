package org.iesch.ad.DocumentosReferenciados.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "autores")
public class AutoresRef {
    @Id
    private String id;

    private String nombre;
    private String nacionalidad;

    public AutoresRef(String nacionalidad, String nombre) {
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
    }
}
