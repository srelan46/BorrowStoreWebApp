package edu.syr.group2.webapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    @Digits(integer = 13,fraction = 0,message = "Enter a valid 13 digit ISBN")
    private long ISBN;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @NotBlank
    private String edition;
    @NotBlank
    private double price;
    private int category;
    @CreationTimestamp
    private LocalDateTime create_time;
    @UpdateTimestamp
    private LocalDateTime update_time;
    @Builder.Default
    private int count = 1;
    private int trade_count;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    public Book()
    {}
    public Book(long ISBN, String author, String title, String edition, double price,int category, int trade_count)
    {
        super();
        this.ISBN=ISBN;
        this.author=author;
        this.title=title;
        this.edition=edition;
        this.price=price;
        this.category=category;
        this.create_time=LocalDateTime.now();
        this.update_time=LocalDateTime.now();
        this.trade_count=trade_count;
    }
    public Long getBookID()
    {
        return bookID;
    }
    public void setID(Long bookID)
    {
        this.bookID=bookID;
    }
    public Long getISBN()
    {
        return ISBN;
    }
    public void setISBN(Long ISBN)
    {
        this.ISBN=ISBN;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author=author;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String Title)
    {
        this.title=title;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price=price;
    }
    public String getEdition() { return edition; }
    public void setCategory(String category){ this.edition=edition;}
    public int getCategory() { return category; }
    public void setCategory(int category){ this.category=category;}
    public LocalDateTime getCreateTime() { return create_time;}
    public void setCreateTime(LocalDateTime create_time){this.create_time=create_time;}
    public LocalDateTime getUpdateTime() {return update_time;}
    public void setUpdateTime(LocalDateTime update_time){this.update_time=update_time;}
    public int getCount() { return count; }
    public void setCount(int count){ this.count=count;}
    public int getTradeCount() { return count; }
    public void setTradeCount(int trade_count){ this.trade_count=trade_count;}
}
