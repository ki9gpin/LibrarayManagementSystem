package com.example.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Component
public class Transaction {
    @Id
    private long id;
    private long userId;
    private String bookISBN;
    private LocalDateTime checkedOutDate;
    private LocalDateTime returnDate;

    public Transaction() {
    }

    public Transaction(long userId, String bookISBN, LocalDateTime checkedOutDate, LocalDateTime returnDate) {
        this.userId = userId;
        this.bookISBN = bookISBN;
        this.checkedOutDate = checkedOutDate;
        this.returnDate = returnDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public LocalDateTime getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(LocalDateTime checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
