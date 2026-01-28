package org.iesch.MongoDemo_Repository.template;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface mongotemplateexample extends MongoRepository<Book,String> {
    @Nullable List<Book> find(Query query, Class<Book> bookClass);
}
