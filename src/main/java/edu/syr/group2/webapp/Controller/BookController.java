package edu.syr.group2.webapp.Controller;

import edu.syr.group2.webapp.Model.Book;
import edu.syr.group2.webapp.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/books/id/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId)
    {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }
    @PostMapping("/AddBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }
    @PostMapping("/AddBookMultiple")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books){
        return ResponseEntity.ok(bookService.saveBooks(books));
    }
    @PutMapping("/UpdateBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.updateBook(book));
    }
    @DeleteMapping("DeleteBook")
    public String removeBook(@RequestBody Long bookId)
    {
        return bookService.deleteBook(bookId);
    }
    @PostMapping("/buy/user/{userId}/id/{bookId}")
    public String buyBook(@PathVariable Long userId,@PathVariable Long bookId) {
        return bookService.buyBook(userId, bookId);
    }
    @PostMapping("/sell/user/{userId}/id/{bookId}")
    public String sellBook(@PathVariable Long userId,@PathVariable Long bookId) {
        return bookService.sellBook(userId, bookId);
    }
    @PostMapping("/sell/user/{userId}/isbn/{ISBN}")
    public String sellBookISBN(@PathVariable Long userId, @PathVariable Long ISBN) {
        return bookService.sellBookISBN(userId, ISBN);
    }
}
