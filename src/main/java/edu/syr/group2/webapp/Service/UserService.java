package edu.syr.group2.webapp.Service;

import edu.syr.group2.webapp.Exception.BookNotFoundException;
import edu.syr.group2.webapp.Exception.UserNotFoundException;
import edu.syr.group2.webapp.Model.Book;
import edu.syr.group2.webapp.Model.User;
import edu.syr.group2.webapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractUserService{
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
        }
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user){
        Optional<User> u = userRepository.findById(user.getuserID());
        if(u.isPresent())
        {
            User userNew = u.get();
            userNew.setUserID(user.getuserID());
            userNew.setUserName(user.getUsername());
            userNew.setFirstName(user.getFirstName());
            userNew.setLastName(user.getLastName());
            userNew.setEmail(user.getEmail());
            userNew.setCreate_time(u.get().getCreate_time());
            userNew.setOwnedBooks(u.get().getOwnedBooks());
            return userRepository.save(userNew);
        }
        throw new UserNotFoundException(u.get().getuserID());
    }
    public String deleteUser(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return "User Deleted: " + user.toString();
    }
}


