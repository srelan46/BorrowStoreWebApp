package edu.syr.group2.webapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger ID;
    @NotBlank
    private String ISBN;
    @NotBlank
    private String Authors;
    @NotBlank
    private String Title;
    @NotBlank
    private String Edition;
    @NotBlank
    private double price;
    private int category;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private int selled;
    private int trade_count;
    public Book()
    {}
    public Book(BigInteger ID, String ISBN, String Authors, String Title, double price,int category,
                LocalDateTime create_time, LocalDateTime update_time,int selled,int trade_count)
    {
        super();
        this.ID=ID;
        this.ISBN=ISBN;
        this.Authors=Authors;
        this.Title=Title;
        this.price=price;
        this.category=category;
        this.create_time=LocalDateTime.now();
        this.update_time=LocalDateTime.now();
        this.selled=selled;
        this.trade_count=trade_count;
    }
    public BigInteger getID()
    {
        return ID;
    }
    public void setID(BigInteger ID)
    {
        this.ID=ID;
    }
    public String getISBN()
    {
        return ISBN;
    }
    public void setISBN(String ISBN)
    {
        this.ISBN=ISBN;
    }
    public String getAuthors()
    {
        return Authors;
    }
    public void setAuthors(String Authors)
    {
        this.Authors=Authors;
    }
    public String getTitle()
    {
        return Title;
    }
    public void setTitle(String Title)
    {
        this.Title=Title;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price=price;
    }
    public int getCategory() { return category; }
    public void setCategory(int category){ this.category=category;}
    public LocalDateTime getCreateTime() { return create_time;}
    public void setCreateTime(LocalDateTime create_time){this.create_time=create_time;}
    public LocalDateTime getUpdateTime() {return update_time;}
    public void setUpdateTime(LocalDateTime create_time){this.update_time=update_time;}
    public int getSelled() { return selled; }
    public void setSelled(int selled){ this.selled=selled;}
    public int getTradeCount() { return selled; }
    public void setTradeCount(int trade_count){ this.trade_count=trade_count;}
}
