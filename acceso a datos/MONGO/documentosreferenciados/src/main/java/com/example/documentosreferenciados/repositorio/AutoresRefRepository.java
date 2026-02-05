package com.example.documentosreferenciados.repositorio;

import com.example.documentosreferenciados.modelo.AutoreRef;
import com.example.documentosreferenciados.modelo.BookRef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoresRefRepository extends MongoRepository<AutoreRef,String> {

    List<AutoreRef> FindbyNombre(String nombre);

    List<AutoreRef> FindbyNacionalidad(String nacionalidad);
    //consulta @query
    // buscar libros con precio inferior y año de publicacion superior a los valores dados
    @Query( "{ 'books': { $elemMatch: { 'precio': { $lt: ?0 }, 'anioPublicacion': { $gt: ?1 } } } }" )
    List<BookRef> buscarLibrosPorPrecioYAno(double precio, int anioPublicacion);
        // Implementación de la consulta personalizada



}
