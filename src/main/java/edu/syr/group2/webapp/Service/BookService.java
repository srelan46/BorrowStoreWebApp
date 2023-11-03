package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Exception.BookNotFoundException;
import edu.syr.group2.webapp.Model.Book;
import edu.syr.group2.webapp.Model.BookCopy;
import edu.syr.group2.webapp.Model.BookStatus;
import edu.syr.group2.webapp.Model.User;
import edu.syr.group2.webapp.Repository.BookCopyRepository;
import edu.syr.group2.webapp.Repository.BookRepository;
import edu.syr.group2.webapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
    public Book getBookByISBN(Long isbn) {
        return bookRepository.findByISBN(isbn).orElseThrow(() -> new BookNotFoundException("ISBN",isbn));
    }
    public Book saveBook(Book book) {
        Book savedBook = bookRepository.save(book);
        int count = book.getCount();
        for(int i=0;i<count;i++)
        {
            BookCopy bookCopy = new BookCopy();
            bookCopy.setBook(savedBook);
            bookCopy.setPrice(savedBook.getPrice());
            bookCopy.setPurchaseDate(LocalDateTime.now());
            bookCopyRepository.save(bookCopy);
        }
        return savedBook;
    }
    public List<Book> saveBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
    public String deleteBook(Long id){
        Book b = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        List<BookCopy> copies = bookCopyRepository.findAllByBook_BookID(id);
        bookCopyRepository.deleteAllByIdInBatch(Collections.singleton(id));
        bookRepository.deleteById(id);
        return "Book Deleted \n"+b.toString();
    }
    public String buyBook(Long userId, Long bookId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if(!userOpt.isPresent())
        {
            return "Failure: User not found";
        }
        if(!bookOpt.isPresent())
        {
            return "Failure: Book not found";
        }
        List<BookCopy> availableCopies = bookCopyRepository.findAllByBook_BookIDAndBookStatus(bookId, BookStatus.AVAILABLE);
        if(availableCopies.isEmpty()){
            return "Failure: No available copies";
        }
        User user = userOpt.get();
        Book book = bookOpt.get();
        BookCopy bookCopy = availableCopies.get(0);
        bookCopy.setUser(user);
        bookCopy.setStatus(BookStatus.CHECKED_OUT);
        Set<BookCopy> s = user.getOwnedBooks();
        s.add(bookCopy);
        user.setOwnedBooks(s);
        bookCopyRepository.save(bookCopy);
        book.setCount(book.getCount()-1);
        bookRepository.save(book);
        userRepository.save(user);
        return "Success + Price: " + bookCopy.getPrice();
    }
    public String sellBook(Long userId, Long bookId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<BookCopy> bookCopyOpt = bookCopyRepository.findById(bookId);
        if(!userOpt.isPresent())
        {
            return "Failure: User not found";
        }
        if(!bookOpt.isPresent())
        {
            return "Failure: Book not found";
        }

        User user = userOpt.get();
        BookCopy bookCopy = bookCopyOpt.get();
        Book book = bookOpt.get();

        if (!bookCopy.getUserID().equals(user.getuserID())) {
            return "Failure: User does not own this book copy";
        }

        user.getOwnedBooks().remove(bookCopy);
        bookCopy.setUser(null);
        // Update the book copy's price and status
        double newPrice = bookCopy.getPrice() * 0.9;
        bookCopy.setPrice(newPrice);
        book.setCount(book.getCount()+1);
        bookCopy.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
        userRepository.save(user);
        bookCopyRepository.save(bookCopy);
        return "Success + Price: " + newPrice;
    }
    public String sellBookISBN(Long userId, Long isbn) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findByISBN(isbn);
        if(!userOpt.isPresent())
        {
            return "Failure: User not found";
        }
        if(!bookOpt.isPresent())
        {
            return "Failure: Book not found";
        }
        Book book = bookOpt.get();
        User user = userOpt.get();
            List<BookCopy> userBookCopies = user.getOwnedBooks().stream()
                    .filter(bookCopy -> bookCopy.getBookISBN().equals(isbn))
                    .collect(Collectors.toList());
        if (userBookCopies.isEmpty()) {
            return "Failure: No book copy with the given ISBN is checked out by the user";
        }
        BookCopy bookCopyToSell = userBookCopies.get(0);
        user.getOwnedBooks().remove(bookCopyToSell);
        bookCopyToSell.setUser(null);
        double newPrice = bookCopyToSell.getPrice() * 0.9; // Assuming a 10% depreciation
        bookCopyToSell.setPrice(newPrice);
        bookCopyToSell.setStatus(BookStatus.AVAILABLE);
        book.setCount(book.getCount()+1);
        bookCopyRepository.save(bookCopyToSell);
        bookRepository.save(book);
        userRepository.save(user);
        return "Success + Price: " + newPrice;
    }
}

