package org.iesch.SegundoSpring;

import org.iesch.SegundoSpring.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SegundoSpringApplication /*implements CommandLineRunner*/ {
	/*@Autowired
	List<Product> products;*/
	public static void main(String[] args) {
		SpringApplication.run(SegundoSpringApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		System.out.println(products);
	}*/
}
