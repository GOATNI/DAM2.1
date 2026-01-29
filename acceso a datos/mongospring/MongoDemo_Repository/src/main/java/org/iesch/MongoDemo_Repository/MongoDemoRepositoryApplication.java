package org.iesch.MongoDemo_Repository;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Clase principal de la aplicación Spring Boot.
 * Implementa CommandLineRunner para ejecutar código al inicio.
 */
@SpringBootApplication
public class MongoDemoRepositoryApplication implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoRepositoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Código a ejecutar al iniciar la aplicación
        // Listar todos los libros existentes
        List<Book> books = bookRepository.findAll();
        System.out.println("Libros iniciales: " + books);

        // Crear y guardar un nuevo libro de ejemplo
        Book book = new Book();
        book.setTitulo("El Quijote");
        book.setEditorial("Editorial Ejemplo");
        book.setPrecio(20.5);
        bookRepository.save(book);

        // Listar libros después de la inserción
        books = bookRepository.findAll();
        System.out.println("Libros tras inserción: " + books);
    }
}
