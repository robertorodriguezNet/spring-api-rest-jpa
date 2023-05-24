package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para la clase Book
 */
@RestController
public class BookController {

    private final BookRepository REPOSITORY;

    private final Logger LOG = LoggerFactory.getLogger(BookController.class);

    public BookController(BookRepository repository) {
        this.REPOSITORY = repository;
    }

    /**
     * Recuperar todos los objetos.
     *
     * @return la colección de objetos
     */
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return this.REPOSITORY.findAll();
    }

    /**
     * Devuelve un libro por un id dado.
     * Optional permite devolver tanto un objeto como null.
     *
     * @param id buscado
     * @return una ResponseEntity
     */
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {

        // Opción 1: no está bien devolver un null
        Optional<Book> book = this.REPOSITORY.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        // Opción 2: expresión funcional
//        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Guardar un libro.
     * Las URL findAll y save son diferentes por el método de envío POST|GET
     * Se recibe el libro por post.
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));

        // Si el libro tiene un id, es que ya está guardado, salimos
        if (book.getId() != null) {
            LOG.warn("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.REPOSITORY.save(book));
    }

    /**
     * Actualiza un libro
     *
     * @param book
     * @return
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {

        // El libro no tiene id, es nuevo, no debería venir a esta ruta
        if (book.getId() == null) {
            LOG.warn("Trying to update a non existent book (0)");
            return ResponseEntity.badRequest().build();
        }

        // El libro tiene un id

        // Comprobar si el libro existe
        if (!REPOSITORY.existsById(book.getId())) {
            LOG.warn("Trying to update a non existent book (1)");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.REPOSITORY.save(book));

    }

    /**
     * Elimina todos los libros
     */
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        LOG.info("REST Request for deleting all books");
        REPOSITORY.deleteAll();
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina el libro dado por el id.
     *
     * @param id del libro que se quiere borrar.
     * @return respuesta indicando que no ya no hay contenido.
     */
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id) {

        if (!REPOSITORY.existsById(id)) {
            LOG.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        REPOSITORY.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
