package com.example.lms.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Component
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_generator")
    private long id;
    private long userId;
    private String bookISBN;
    private LocalDateTime checkedOutDate;
    private LocalDateTime returnDate;
    @ManyToOne
    @JoinColumn
    private Member member;

    @ManyToOne
    @JoinColumn
    private Book book;

    public Transaction() {
    }

    public Transaction(long userId, String bookISBN, LocalDateTime checkedOutDate, LocalDateTime returnDate, Member member, Book book) {
        this.userId = userId;
        this.bookISBN = bookISBN;
        this.checkedOutDate = checkedOutDate;
        this.returnDate = returnDate;
        this.member = member;
        this.book = book;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
