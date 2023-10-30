package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Model.Book;
import edu.syr.group2.webapp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Double buyBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            bookRepository.delete(book);
            return book.getPrice();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    public Double sellBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Double newPrice=book.getPrice();
            newPrice = newPrice * 0.9;
            book.setPrice(newPrice);
            bookRepository.save(book);
            return newPrice;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    public Double sellBookByISBN(String ISBN) {
        Optional<Book> bookOptional = bookRepository.findByISBN(ISBN);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Double newPrice=book.getPrice();
            newPrice = newPrice * 0.9;
            book.setPrice(newPrice);
            bookRepository.save(book);
            return newPrice;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}

