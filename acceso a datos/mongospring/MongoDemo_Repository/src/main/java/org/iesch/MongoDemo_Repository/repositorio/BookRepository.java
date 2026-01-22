package org.iesch.MongoDemo_Repository.repositorio;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // =============QUERY METHODS =============

List<Book> findByTituloContainingIgnoreCase(String titulo);

List<Book> findByAutorNombreContainingIgnoreCase(String autor);


    // =============QUERY PERSONALIZADOS =============
}
