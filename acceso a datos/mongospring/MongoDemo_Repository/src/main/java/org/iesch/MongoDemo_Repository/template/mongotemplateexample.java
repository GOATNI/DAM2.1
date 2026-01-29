package org.iesch.MongoDemo_Repository.template;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Componente de repositorio personalizado que utiliza MongoTemplate.
 * Permite realizar consultas dinámicas y complejas que no son posibles
 * o son difíciles de expresar con los métodos derivados de MongoRepository.
 */
@Repository
public class mongotemplateexample {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Ejecuta una consulta personalizada utilizando MongoTemplate.
     *
     */
    public List<Book> find(Query query, Class<Book> bookClass) {
        return mongoTemplate.find(query, bookClass);
    }
}
