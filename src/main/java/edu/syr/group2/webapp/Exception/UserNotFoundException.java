package edu.syr.group2.webapp.Exception;

public class UserNotFoundException extends LibraryException{
    public UserNotFoundException(Long id)
    {
        super("User with ID "+id+" not found.");
    }
}
