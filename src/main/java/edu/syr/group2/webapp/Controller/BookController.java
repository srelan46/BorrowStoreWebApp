package edu.syr.group2.webapp.Controller;

import edu.syr.group2.webapp.Model.Book;
import edu.syr.group2.webapp.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/AddBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @PostMapping("/buy/{bookId}/user/{userId}")
    public String buyBook(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.buyBook(userId, bookId);
    }

    @PostMapping("/sell/{bookId}/user/{userId}")
    public String sellBook(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.sellBook(userId, bookId);
    }
}
