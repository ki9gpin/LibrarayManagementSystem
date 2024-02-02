package com.example.lms.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class TransactionDTO {

    private long userId;
    private String bookISBN;
    private long transactionId;
    public TransactionDTO() {
    }

    public TransactionDTO(long userId, String bookISBN, long transactionId) {
        this.userId = userId;
        this.bookISBN = bookISBN;
        this.transactionId = transactionId;
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

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
