package org.iesch.MongoDemo_Repository.service;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.template.mongotemplateexample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que utiliza MongoTemplate para realizar consultas avanzadas.
 */
@Service
public class MongoTemplateService {

    @Autowired
    mongotemplateexample mongotemplateexample;

    /**
     * Busca libros por título ignorando mayúsculas y minúsculas.
     *
     */
    public List<Book> findByTituloIgnoreCase(String q) {
        Query query = new Query();
        query.addCriteria(Criteria.where("titulo").regex(q, "i"));
        return mongotemplateexample.find(query, Book.class);
    }

    /**
     * Busca libros por categoría ignorando mayúsculas y minúsculas.
     *

     */
    public List<Book> findByCategoriasIgnoreCase(String q) {
        Query query = new Query();
        query.addCriteria(Criteria.where("categorias").regex(q, "i"));
        return mongotemplateexample.find(query, Book.class);
    }

    /**
     * Busca libros por nombre de autor.
     *

     */
    public List<Book> findByAutorIgnoreCase(String q) {
        Query query = new Query();
        query.addCriteria(Criteria.where("autores.nombre").is(q));
        return mongotemplateexample.find(query, Book.class);
    }

    /**
     * Busca libros dentro de un rango de precios.
     *

     */
    public List<Book> findByprecioIgnoreCase(double max, double min) {
        Query query = new Query();
        // Corregido: .in(min, max) busca valores exactos en la lista [min, max].
        // Para rango se debe usar .gte(min).lte(max).
        query.addCriteria(Criteria.where("precio").gte(min).lte(max));
        return mongotemplateexample.find(query, Book.class);
    }

    /**
     * Busca libros por año de publicación.
     *

     */
    public List<Book> findByAnioPublicacion(int anio) {
        Query query = new Query();
        query.addCriteria(Criteria.where("anioPublicacion").is(anio));
        return mongotemplateexample.find(query, Book.class);
    }

    /**
     * Busca libros publicados después de un año específico y dentro de un rango de precio.
     *

     */
    public List<Book> findByAnioAndPrecioRange(int anio, double min, double max) {
        Query query = new Query();
        query.addCriteria(Criteria.where("anioPublicacion").gt(anio)
                .and("precio").gte(min).lte(max));
        return mongotemplateexample.find(query, Book.class);
    }
}
