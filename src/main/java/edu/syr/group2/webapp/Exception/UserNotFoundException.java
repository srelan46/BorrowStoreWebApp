package edu.syr.group2.webapp.Exception;

import edu.syr.group2.webapp.Model.User;

public class UserNotFoundException extends LibraryException{
    public UserNotFoundException()
    {
        super("Default User Not Found");
    }
    public UserNotFoundException(Long id)
    {
        super("User with ID "+id+" not found.");
    }
}
