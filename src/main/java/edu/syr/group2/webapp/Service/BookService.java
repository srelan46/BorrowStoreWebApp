package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Model.Book;
import edu.syr.group2.webapp.Model.User;
import edu.syr.group2.webapp.Repository.BookRepository;
import edu.syr.group2.webapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBook(long id) {return bookRepository.findById(id).orElse(null);}
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> saveBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
    public String deleteBook(long id){
        Book b = bookRepository.findById(id).orElse(null);
        bookRepository.deleteById(id);
    return "Book Deleted \n book details: \n"+b.toString();
    }
    public String buyBook(Long userId, Long bookId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (userOpt.isPresent() && bookOpt.isPresent()) {
            User user = userOpt.get();
            Book book = bookOpt.get();
            int bookCount=book.getCount();
            if (bookCount>0) {
                Set<Book> s = user.getOwnedBooks();
                s.add(book);
                user.setOwnedBooks(s);
                userRepository.save(user);
                book.setCount(bookCount-1);
                bookRepository.save(book);
                return "Success + Price: " + book.getPrice();
            } else {
                return "Failure: Book Not available";
            }
        }
        return "Failure: User or Book not found";
    }
    public String sellBook(Long userId, Long bookId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (userOpt.isPresent() && bookOpt.isPresent()) {
            User user = userOpt.get();
            Book book = bookOpt.get();
            Set<Book> s = user.getOwnedBooks();
            s.remove(book);
            user.setOwnedBooks(s);
            double newPrice = book.getPrice() * 0.9;
            book.setPrice(newPrice);
            book.setCount(book.getCount()+1);
            bookRepository.save(book);

            return "Success + Price: " + newPrice;
        }
        return "Failure: User or Book not found";
    }
    public String sellBookISBN(Long userId, Long isbn) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findByISBN(isbn);

        if (userOpt.isPresent() && bookOpt.isPresent()) {
            User user = userOpt.get();
            Book book = bookOpt.get();
            Set<Book> s = user.getOwnedBooks();
            s.remove(book);
            user.setOwnedBooks(s);
            double newPrice = book.getPrice() * 0.9;
            book.setPrice(newPrice);
            book.setCount(book.getCount()+1);
            bookRepository.save(book);
            return "Success + Price: " + newPrice;
        }
        return "Failure: User or Book not found";
    }
}

