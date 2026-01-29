package org.iesch.MongoDemo_repository.service;

import org.iesch.MongoDemo_repository.model.Book;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    MongoTemplate mongoTemplate;

    //el nullable es que puede devolver nulos , se puede dejar o no
    public @Nullable List<Book> findByTituloContainingIgnoreCase(String titulo) {
        //Importante importar bien Query: import org.springframework.data.mongodb.core.query.Query;
        Query query = new Query();
        query.addCriteria(Criteria.where("titulo").regex(titulo, "i"));
        return mongoTemplate.find(query, Book.class);

    }

    public @Nullable List<Book> findByCategoriaIgnoreCase(String cat) {
        //Esto va a buscar dentro del array de categorias ya que es un documento embebido.
        Query query = new Query();
        query.addCriteria(Criteria.where("categorias").regex(cat, "i"));
        return mongoTemplate.find(query, Book.class);
    }

    public @Nullable List<Book> findByAutor(String nombre) {

        //Vamos a buscar por autor , es un array llamado autores y dentro tiene nombre y nacionalidad
        Query query = new Query();
        query.addCriteria(Criteria.where("autores.nombre").is(nombre)); //Esto al ser un is ,
        // no funciona como un regex entonces a nada que nos equivoquemos en la ruta buscando por nombre de autor no nos encontrara nada

        return mongoTemplate.find(query, Book.class);
    }

    public @Nullable List<Book> findByPrecioBetween(Double min, Double max) {
        //Vamos a buscar entre un rango de precios.
        Query query = new Query();
        query.addCriteria(Criteria.where("precio").gte(min).lte(max)); //Greater tan equal y Less tahn equal
        return mongoTemplate.find(query,Book.class);

    }

    public @Nullable List<Book> findByAioPublicacionReciente(Integer anio) {
        Query query = new Query();
        query.addCriteria(Criteria.where("anioPublicacion").gt(anio));
        return mongoTemplate.find(query,Book.class);
    }

    public @Nullable List<Book> findByPrecioAnio(Double precio, Integer anio) {
        //Vamos a buscar libros por precios inferiores a los que nos pasan y de anio publicacion mayor al que nos pasan
        Query query = new Query();
        query.addCriteria(Criteria.where("precio").lt(precio).and("anioPublicacion").gt(anio));
        return mongoTemplate.find(query,Book.class);
    }
}
