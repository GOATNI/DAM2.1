package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoresRefRepository extends MongoRepository<AutoresRef, String> {


    /**
     * Buscar por nombre de autor ignorando mayúsculas y minúsculas
     *
     */

    List<AutoresRef> findByNombreContainingIgnoreCase(String nombre);


    /**
     * Buscar por nacionalidad ignorando mayúsculas y minúsculas
     */
    List<AutoresRef> findByNacionalidadContainingIgnoreCase(String pais);

    /**
     * Buscar por nacionalidades de una lista...
     */
    List<AutoresRef> findByNacionalidadIn(List<String> paises);
}
