package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Exception.BookCopyNotFoundException;
import edu.syr.group2.webapp.Exception.BookNotFoundException;
import edu.syr.group2.webapp.Model.BookCopy;
import edu.syr.group2.webapp.Repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService extends AbstractBookCopyService{
    @Autowired
    private BookCopyRepository bookCopyRepository;
    public List<BookCopy> findAllBookCopies() {
        return bookCopyRepository.findAll();
    }
    public BookCopy findBookCopyById(Long copyId) {
        return bookCopyRepository.findById(copyId).orElseThrow(() -> new BookNotFoundException(copyId));
    }
    public BookCopy createBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }
    public String deleteBookCopy(Long copyId) {
        BookCopy bookCopy = bookCopyRepository.findById(copyId).orElseThrow(() -> new BookCopyNotFoundException(copyId));
        bookCopyRepository.deleteById(copyId);
        return bookCopy.toString();
    }
}
