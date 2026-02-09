package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRefRepository extends MongoRepository<BookRef,String> {


    /**
     * Buscar libros por el id de un autor
     */
    List<BookRef> findByAutoresId(String autorId);


    //Consulta con @Query
    /**
     * Buscar libros con precio inferior a un valor dado y año de publicación posterior a un año dado
     */
    @Query("{'precio': {$lt: ?0}, 'anioPublicacion': {$gte: ?1}}")
    List<BookRef> buscarPorPrecioInferiorYAnioSuperior(Double precio, Integer anio);

    @Query("{$or: [{'precio': {$lt: ?0}}, {'anioPublicacion': {$lt: ?1}}]}")
    @Nullable List<BookRef> buscarLibrosEconomicosOAntiguos(Double precio, Integer anio);
}
