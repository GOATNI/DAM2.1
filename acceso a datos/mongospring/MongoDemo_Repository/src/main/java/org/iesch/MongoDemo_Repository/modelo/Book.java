package org.iesch.MongoDemo_Repository.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Clase que representa un libro en la base de datos MongoDB.
 * Utiliza Lombok para generar getters, setters, constructores y toString.
 */
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "libros")
public class Book {

    @Id
    private String id;
    private String titulo;
    private String isbn;
    private Integer anioPublicacion;
    private Double precio;
    private Integer numeroPaginas;
    private String editorial;

    // Documentos embebidos
    private List<Autor> autores;
    private List<String> categorias;

    /**
     * Constructor personalizado para crear un libro sin ID (el ID lo genera MongoDB).

     */
    public Book(String titulo, String isbn, Integer anioPublicacion, Double precio, Integer numeroPaginas, String editorial, List<Autor> autores, List<String> categorias) {
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
