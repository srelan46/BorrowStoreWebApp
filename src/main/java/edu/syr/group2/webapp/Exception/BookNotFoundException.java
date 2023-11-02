package edu.syr.group2.webapp.Exception;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(long bookId)
    {
        super("Book with ID " + bookId + " not found.");
    }
    public BookNotFoundException(String ISBN, long value) {
        super("Book with " + ISBN + " " + value + " not found.");
    }
}
