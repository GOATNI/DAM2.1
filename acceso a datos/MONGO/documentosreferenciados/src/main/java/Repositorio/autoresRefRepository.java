package Repositorio;

import modelo.AutoreRef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface autoresRefRepository extends MongoRepository<AutoreRef,String> {
}
