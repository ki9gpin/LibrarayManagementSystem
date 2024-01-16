package com.example.lms.dto;

public class TransactionDTO {

    private long userId;
    private String bookISBN;

    public TransactionDTO() {
    }

    public TransactionDTO(long userId, String bookISBN) {
        this.userId = userId;
        System.out.println("in transaction dto constructor bookISBN = "+ bookISBN);
        this.bookISBN = bookISBN;
    }

    public long getUserId() {
        return userId;
    }
    public String getBookISBN() {
        return bookISBN;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }
}
