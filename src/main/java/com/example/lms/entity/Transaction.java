package com.example.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    private long id;
    private long userId;
    private String bookISBN;
    private LocalDateTime checkedOutDate;
    private LocalDateTime returnDate;
}
