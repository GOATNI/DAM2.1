package org.iesch.MongoDemo_Repository;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class    MongoDemoRepositoryApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;
	public static void main(String[] args) {
		SpringApplication.run(MongoDemoRepositoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Código a ejecutar al iniciar la aplicación
		List<Book> books = bookRepository.findAll();
		System.out.println(books);

		Book book = new Book();
		book.setTitulo("El Quijote");
		bookRepository.save(book);


		books = bookRepository.findAll();
		System.out.println(books);
	}

}
