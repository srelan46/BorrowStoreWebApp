package edu.syr.group2.webapp.Exception;

import edu.syr.group2.webapp.Model.BookCopy;

public class BookCopyNotFoundException extends LibraryException {
    public BookCopyNotFoundException(Long copyId)
    {
        super("Book Copy with ID " + copyId + " not found.");
    }
}
