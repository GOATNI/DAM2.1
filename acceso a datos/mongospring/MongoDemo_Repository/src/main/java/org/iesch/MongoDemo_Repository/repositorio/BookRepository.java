package org.iesch.MongoDemo_Repository.repositorio;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Book.
 * Extiende de MongoRepository para proporcionar operaciones CRUD básicas.
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // ============= QUERY METHODS =============

    /**
     * Busca libros cuyo título contenga la cadena proporcionada, ignorando mayúsculas y minúsculas.
     *

     */
    List<Book> findByTituloContainingIgnoreCase(String titulo);

    /**
     * Busca libros por el nombre del autor, ignorando mayúsculas y minúsculas.
     * Dado que 'autores' es una lista en Book y 'Autor' tiene un campo 'nombre',
     * la convención correcta es findByAutoresNombreContainingIgnoreCase.
     */
    List<Book> findByAutoresNombreContainingIgnoreCase(String autor);

    // ============= QUERY PERSONALIZADOS =============
}
