package org.iesch.MongoDemo_repository.repository;

import org.iesch.MongoDemo_repository.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    //==================QUERY METHODS=========================

    //Buscar libros por titulo Ademas ignore Mayusculas y minusculas
    List<Book> findByTituloContainingIgnoreCase (String titulo);

    //Buscar libros por categoria
    //Como categorias es un elemento iterable SpringData buscará si contiene el elemento
    List<Book> findByCategoriasIgnoreCase (String categoria);

    //Buscar libros por nombre de autor.
    //Documento embebid
    List<Book> findByAutoresNombre (String nombre);

    //Buscar libros emtre un rango de precios

    List<Book> findByPrecioBetween (Double min,Double max);

    //Libros que se han publicado despues de un año completo

    List<Book> findByAnioPublicacionGreaterThan(Integer anio);

    //==================QUERY PERSONALIZADAS==================

    //Buscar por nombre de autor usando Query Mongo.
    @Query("{'autores.nombre': {$regex: ?0, $options: 'i'}}")
    List<Book> buscarPorNombreAutor(String nombreAutor);


    //Buscar libros con un precio inferior a x y que se hayan publicado despues del año x
    @Query("{'precio': {$lt: ?0} , 'AnioPublicacion': {$gte: ?1}}")
    List<Book> buscarPorPrecioInferiorYAnioMayor(Double precio, Integer anioPublicacion);
}
