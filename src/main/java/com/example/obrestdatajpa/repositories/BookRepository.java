package com.example.obrestdatajpa.repositories;

import com.example.obrestdatajpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Los parámetros para JpaRepository son el tipo de objeto
 * que se va a guardar y el tipo de la PK.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
