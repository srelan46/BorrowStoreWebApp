package edu.syr.group2.webapp.Repository;

import edu.syr.group2.webapp.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByISBN(String ISBN);
}
