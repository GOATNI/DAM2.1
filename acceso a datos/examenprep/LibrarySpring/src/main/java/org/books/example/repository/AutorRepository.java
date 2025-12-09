package org.books.example.repository;

import org.books.example.modelo.Autor;
import org.books.example.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AutorRepository extends JpaRepository<Autor, Long> {

}
