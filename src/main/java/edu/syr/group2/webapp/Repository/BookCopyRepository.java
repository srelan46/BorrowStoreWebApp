package edu.syr.group2.webapp.Repository;

import edu.syr.group2.webapp.Model.Book;
import edu.syr.group2.webapp.Model.BookCopy;

import edu.syr.group2.webapp.Model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy,Long> {
    List<BookCopy> findAllByBook_BookIDAndBookStatus(Long bookId, BookStatus bookStatus);
    List<BookCopy> findAllByBook_BookID(Long bookId);
}
