package org.iesch.ad.DocumentosReferenciados.service;

import com.mongodb.lang.Nullable;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookRefService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public @Nullable List<BookRef> buscarTodos() {
        return mongoTemplate.findAll(BookRef.class);
    }

    public @org.jspecify.annotations.Nullable List<BookRef> findByTituloContainingIgnoreCase(String titulo) {
        org.springframework.data.mongodb.core.query.Query query = new Query();
        query.addCriteria(Criteria.where("titulo").regex(".*" + titulo + ".*", "i"));
        return mongoTemplate.find(query, BookRef.class);
    }

    public @org.jspecify.annotations.Nullable List<BookRef> findByAutorId(String autorId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("autores.id").is(autorId));
        return mongoTemplate.find(query, BookRef.class);
    }

    public @org.jspecify.annotations.Nullable BookRef buscaOne(String id) {
        return mongoTemplate.findById(id, BookRef.class);
    }

    public @org.jspecify.annotations.Nullable List<BookRef> findByAutorNombre(String nombreAutor) {
        //1 - lookup, luego un match
        LookupOperation lookupStore = LookupOperation.newLookup()
                .from("autores")
                .localField("autores")
                .foreignField("_id")
                .as("autoresData");
        MatchOperation matchStage = Aggregation.match(
                Criteria.where("autoresData.nombre").regex(nombreAutor, "i")
        );
        Aggregation aggregation = Aggregation.newAggregation(lookupStore, matchStage);
        AggregationResults<BookRef> results = mongoTemplate.aggregate(
                aggregation,"libros_ref", BookRef.class
        );
        return results.getMappedResults();
    }
}
