package org.iesch.MongoDemo_Repository.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa un autor.
 * Se utiliza como documento embebido dentro de la colección de libros.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "autores")
public class Autor {
    private String nombre;
    private String nacionalidad;
}
