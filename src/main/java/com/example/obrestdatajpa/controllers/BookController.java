package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para la clase Book
 */
@RestController
public class BookController {

    private final BookRepository REPOSITORY;

    public BookController(BookRepository repository) {
        this.REPOSITORY = repository;
    }

    /**
     * Recuperar todos los objetos.
     * @return la colección de objetos
     */
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return this.REPOSITORY.findAll();
    }

    /**
     * Devuelve un libro por un id dado
     * @param id buscado
     * @return el libro pedido
     */
    public Book findById(Long id){
        return null;
    }

    /**
     * Elimina todos los libros
     */
    public void deleteAll(){

    }

    /**
     * Elimina el libro dado por el id
     * @param id del libro que se quiere borrar
     */
    public void deleteById(Long id){

    }

}
