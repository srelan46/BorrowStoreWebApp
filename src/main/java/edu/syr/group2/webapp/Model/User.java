package edu.syr.group2.webapp.Model;

import edu.syr.group2.webapp.Repository.UserRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @NotBlank
    @Column(name="username")
    private String username;
    @NotBlank
    @Column(name="firstname")
    private String firstname;
    @NotBlank
    @Column(name="lastname")
    private String lastname;
    @NotBlank
    @Column(name="email")
    private String email;
    @NotBlank
    @Column(name="create_time")
    @CreationTimestamp
    private LocalDateTime create_time;
    @NotBlank
    @Column(name="update_time")
    @UpdateTimestamp
    private LocalDateTime update_time;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_books",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "bookID"))
    private Set<BookCopy> ownedBooks;

    public User(){}
    public User(Long userID,String username,String firstname,String lastname,String email)
    {
        super();
        this.userID=userID;
        this.username=username;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.create_time=LocalDateTime.now();
        this.update_time=LocalDateTime.now();
        this.ownedBooks=new HashSet<>();
    }
    public Long getuserID() {return userID;}

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public Set<BookCopy> getOwnedBooks(){return ownedBooks;}

    // Setters
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public void setOwnedBooks(Set<BookCopy> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }
    @Override
    public String toString()
    {
        return "ID: "+this.getuserID()+
                "\nUsername: "+this.getUsername()+
                "\nFirstName: "+this.getFirstName()+
                "\nLastName: "+this.getLastName()+
                "\nEmail: "+this.getEmail()+
                "\nCreate_time: "+this.getCreate_time()+
                "\nUpdate_time: "+this.getUpdate_time()+
                "\nOwnedBooks: "+this.getOwnedBooks();
    }
}
