package edu.syr.group2.webapp.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import edu.syr.group2.webapp.Model.BookStatus;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @NotBlank
    private double price;
    private LocalDateTime purchaseDate;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus = BookStatus.AVAILABLE;

    public BookCopy(){}
    public void setBook(Book book) {
        this.book = book;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public void setUser(User user) {
        this.user=user;
    }
    public void setStatus(BookStatus bookStatus) {
        this.bookStatus=bookStatus;
    }
    public double getPrice() {
        return price;
    }
    public LocalDateTime getPurchaseDate()
    {
        return purchaseDate;
    }
    public BookStatus getStatus()
    {
        return bookStatus;
    }
    public Long getUserID() {
        return user.getuserID();
    }
    public Long getCopyId(){
        return copyId;
    }

    public Book getBook() {
        return book;
    }
}