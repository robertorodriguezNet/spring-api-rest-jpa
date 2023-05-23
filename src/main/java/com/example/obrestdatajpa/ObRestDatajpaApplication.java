package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class ObRestDatajpaApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
        BookRepository repository = context.getBean(BookRepository.class);

        // Crear un libro
        Book book1 = new Book(null, "Homo Deus", "Yuvel Noah", 450, 29.99, LocalDate.of(2018, 12, 1), true);
        Book book2 = new Book(null, "Homo Sapiens", "Yuvel Noah", 450, 19.99, LocalDate.of(2013, 6, 22), true);

		System.out.println("Libros en la base de datos: " + repository.count() );

        // Almacenar un libro
        repository.save(book1);
        repository.save(book2);

		System.out.println("Libros en la base de datos: " + repository.count() );

		// Recuperar todos los libros
		ArrayList<Book> books = (ArrayList<Book>) repository.findAll();
		books.forEach( b -> System.out.println( b.getTitle()));

        // Borrar un libro
//		repository.deleteById(1L);

		System.out.println("Libros en la base de datos: " + repository.count() );

	}

}
