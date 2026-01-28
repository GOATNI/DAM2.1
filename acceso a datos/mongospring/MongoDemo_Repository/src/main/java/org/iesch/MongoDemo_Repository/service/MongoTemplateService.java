package org.iesch.MongoDemo_Repository.service;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.template.mongotemplateexample;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Queue;

@Service
public class MongoTemplateService {
    @Autowired
    mongotemplateexample mongotemplateexample;
    public @Nullable List<Book> findByTituloIgnoreCase(String q){
        Query query = new Query();
        query.addCriteria(Criteria.where("titulo").regex(q,"i"));

        return mongotemplateexample.find(query,Book.class);
    }


    public List<Book> findByCategoriasIgnoreCase(String q) {
        Query query = new Query();
        query.addCriteria(Criteria.where("categorias").regex(q,"i"));
        return mongotemplateexample.find(query, Book.class);
    }

    public @Nullable List<Book> findByAutorIgnoreCase(String q) {
        Query query = new Query();
        query.addCriteria(Criteria.where("autores.nombre").is(q));
        return mongotemplateexample.find(query, Book.class);
    }

    public @Nullable List<Book> findByprecioIgnoreCase(double max, double min) {
        Query query = new Query();
        query.addCriteria(Criteria.where("precio").in(min,max));
        return mongotemplateexample.find(query, Book.class);
    }
}
