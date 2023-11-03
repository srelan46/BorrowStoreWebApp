package edu.syr.group2.webapp.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import edu.syr.group2.webapp.Model.BookStatus;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Builder;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
    @NotBlank
    private double price;
    private LocalDateTime purchaseDate;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus = BookStatus.AVAILABLE;
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
    //Getter Methods
    public Long getCopyId(){return copyId;}
    public Long getBookID() {
        return book.getBookID();
    }
    public Long getBookISBN(){return book.getISBN();}
    public String getBookTitle(){return book.getTitle();}
    public double getPrice() {return price;}
    public LocalDateTime getPurchaseDate() {return purchaseDate;}
    public BookStatus getStatus() {return bookStatus;}
    public Long getUserID() {return this.user.getuserID();}
    @Override
    public String toString()
    {
        return "CopyID: "+this.getCopyId()+
                "\nBookID: "+this.getBookID()+
                "\nISBN: "+this.getBookISBN()+
                "\nTitle: "+this.getBookTitle()+
                "\nPrice: "+this.getPrice()+
                "\nPurchaseDate: "+this.getPurchaseDate()+
                "\nStatus: "+this.getStatus()+
                "\nUserID: "+this.getUserID();
    }
}