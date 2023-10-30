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

    /*
    @PostMapping("/buy/{id}")
    public ResponseEntity<Double> buyBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.buyBook(id));
    }

    @PostMapping("/sell/id/{id}")
    public ResponseEntity<Double> sellBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.sellBookById(id));
    }

    @PostMapping("/sell/isbn/{isbn}")
    public ResponseEntity<Double> sellBookByISBN(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.sellBookByISBN(isbn));
    }*/
}
