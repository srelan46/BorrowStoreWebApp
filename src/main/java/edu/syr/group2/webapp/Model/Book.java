package edu.syr.group2.webapp.Model;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger ID;
    private String ISBN;
    private String Author;
    private String Title;
    private String Edition;
    private double price;
    private int category;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private int selled;
    private int trade_count;
}
