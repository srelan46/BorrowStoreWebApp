package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Model.User;

import java.util.List;

public abstract class AbstractUserService {
    abstract List<User> getAllUsers();
    abstract User getUser(Long id);
    abstract User createUser(User user);
    abstract User updateUser(User user);
    abstract String deleteUser(long id);
}
