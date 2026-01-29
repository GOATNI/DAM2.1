package org.iesch.MongoDemo_repository.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "libros")
public class Book {
    @Id
    @Field("_id")
    private String id;
    private String titulo;
    private String isbn;
    private Integer anioPublicacion;
    private double precio;
    private Integer numeroPaginas;
    private String editorial;

    //En documentos embebidos
    private List<Autor> autores;
    private List<String> categorias;

    public Book(String titulo, String isbn, Integer anioPublicacion, double precio, Integer numeroPaginas, String editorial, List<Autor> autores, List<String> categorias) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.precio = precio;
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
        this.autores = autores;
        this.categorias = categorias;
    }

}
