package edu.syr.group2.webapp.Controller;

import edu.syr.group2.webapp.Model.BookCopy;
import edu.syr.group2.webapp.Service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookcopies")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;
    @GetMapping("/")
    public ResponseEntity<List<BookCopy>> getAllBookCopies() {
        List<BookCopy> bookCopies = bookCopyService.findAllBookCopies();
        return ResponseEntity.ok(bookCopies);
    }
    @GetMapping("/{copyId}")
    public ResponseEntity<BookCopy> getBookCopyById(@PathVariable Long copyId) {
        return ResponseEntity.ok(bookCopyService.findBookCopyById(copyId));
    }
    @PostMapping("/")
    public ResponseEntity<BookCopy> createBookCopy(@RequestBody BookCopy bookCopy) {
        return ResponseEntity.ok(bookCopyService.createBookCopy(bookCopy));
    }
    @DeleteMapping("/{copyId}")
    public String deleteBookCopy(@PathVariable Long copyId) {
        return bookCopyService.deleteBookCopy(copyId);
    }
}