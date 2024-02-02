package com.example.lms.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Entity
@Component
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long userId;
    private List<String> booksIsbn;
    private LocalDate checkedOutDate;
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn
    private Member member;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<BookWithDate> booksWithDate;

    public Transaction() {
    }

    public Transaction(long id, long userId, List<String> booksIsbn, LocalDate checkedOutDate, LocalDate returnDate, Member member, List<BookWithDate> booksWithDate) {
        this.id = id;
        this.userId = userId;
        this.booksIsbn = booksIsbn;
        this.checkedOutDate = checkedOutDate;
        this.returnDate = returnDate;
        this.member = member;
        this.booksWithDate = booksWithDate;
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

    public LocalDate getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(LocalDate checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<BookWithDate> getBooksWithDate() {
        return booksWithDate;
    }

    public void setBooksWithDate(List<BookWithDate> books) {
        this.booksWithDate = books;
    }

    public List<String> getBooksIsbn() {
        return booksIsbn;
    }

    public void setBooksIsbn(List<String> booksIsbn) {
        this.booksIsbn = booksIsbn;
    }
}
