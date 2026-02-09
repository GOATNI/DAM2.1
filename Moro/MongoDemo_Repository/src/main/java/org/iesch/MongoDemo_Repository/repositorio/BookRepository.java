package org.iesch.MongoDemo_Repository.repositorio;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // =============QUERY METHODS =============

    /**
     * Buscar libros por título. (Ignorar mayúsculas y minúsculas)
     */

    List<Book> findByTituloContainingIgnoreCase(String titulo);


    /**
     * Buscar libros por categoria.)
     *
     * Como categorias es un array, busca libros que contengan la categoria pasada como parámetro.
     */

    List<Book> findByCategorias(String categoria);

    /**
     * Buscar libros por autor. (Ignorar mayúsculas y minúsculas)
     */

    List<Book> findByAutoresNombre(String nombre);

    /**
     * Buscar libros que esten en un rango de precio.
     */

    List<Book>  findByPrecioBetween(double precioMinimo, double precioMaximo);

    /**
     * Buscar los libros que se hayan publicado despues de un año determinado.
     */

    List<Book>findByAnioPublicacionGreaterThan(Integer anio);





    // =============QUERY PERSONALIZADOS =============

    /**
     * Buscar por nombre de autor (usando @Query)
     */

    @Query("{'autores.nombre': {$regex: ?0, $options: 'i'}}")
    List<Book> buscarPorNombre(String nombre);

    /**
     * Buscar libros con un precio inferior a un valor dado y que se hayan publicado despues de un año determinado., usando @Query
     */

    @Query("{ 'precio': {$lt: ?0}, 'anioPublicacion': {$gt: ?1} }")
    List<Book> buscarPorPrecioInferiorYAnioMayor(double precio, Integer anio);

}
