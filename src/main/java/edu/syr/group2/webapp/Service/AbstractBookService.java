package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Model.Book;

import java.util.List;

public abstract class AbstractBookService {
    abstract List<Book> getAllBooks();
    abstract Book getBookById(Long id);
    abstract Book getBookByISBN(Long isbn);
    abstract Book saveBook(Book book);
    abstract List<Book> saveBooks(List<Book> books);
    abstract Book updateBook(Book book);
    abstract String deleteBook(Long id);
    abstract String buyBook(Long userId, Long bookId);
    abstract String sellBook(Long userId, Long bookId);
    abstract String sellBookISBN(Long userId, Long bookId);
}
