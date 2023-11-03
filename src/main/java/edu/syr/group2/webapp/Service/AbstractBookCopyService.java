package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Model.BookCopy;

import java.util.List;

public abstract class AbstractBookCopyService{
    abstract List<BookCopy> findAllBookCopies();
    abstract BookCopy findBookCopyById(Long copyId);
    abstract BookCopy createBookCopy(BookCopy bookCopy);
    abstract String deleteBookCopy(Long copyId);
}
