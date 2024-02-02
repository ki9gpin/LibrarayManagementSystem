package com.example.lms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookWithDate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;
    private LocalDate checkedOutDate;
    private LocalDate returnDate;

    public BookWithDate() {
    }

    public BookWithDate(Book book, LocalDate checkedOutDate, LocalDate returnDate) {
        this.book = book;
        this.checkedOutDate = checkedOutDate;
        this.returnDate = returnDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

}
