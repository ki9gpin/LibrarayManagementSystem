package com.example.lms.service;

import com.example.lms.entity.Transaction;
import com.example.lms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionByUserId(long id) {

        return transactionRepository.findById(id);
    }

    public List<Transaction> getTransactionByBookISBN(String isbn) {
        return transactionRepository.findTransactionByBookISBN(isbn);
    }

    public Transaction checkOut(Transaction transaction, long userId, String isbn) {
        transaction.setBookISBN(isbn);
        transaction.setUserId(userId);
        transaction.setCheckedOutDate(LocalDateTime.now());
        transaction.setReturnDate(null);
        return transactionRepository.save(transaction);
    }

    public Transaction returnBook(long userId, String isbn) {
        Transaction transaction =  transactionRepository.findTransactionByBookISBNAndUserId(isbn,userId);
        transaction.setReturnDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}
