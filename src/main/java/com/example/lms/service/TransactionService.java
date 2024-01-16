package com.example.lms.service;

import com.example.lms.dto.TransactionDTO;
import com.example.lms.entity.Transaction;
import com.example.lms.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionByUserId(long id) {

        return transactionRepository.findById(id);
    }

    public List<Transaction> getTransactionByBookISBN(String isbn) {
        return transactionRepository.findTransactionByBookISBN(isbn);
    }

    public Transaction checkOut(TransactionDTO transactionDTO) {
        if(transactionDTO.getBookISBN()!=null){

            Transaction transaction = new Transaction();
            transaction.setBookISBN(transactionDTO.getBookISBN());
            transaction.setUserId(transactionDTO.getUserId());
            transaction.setCheckedOutDate(LocalDateTime.now());
            transaction.setReturnDate(null);
            return transactionRepository.save(transaction);
        }
        else return null;
    }

    public Transaction returnBook(TransactionDTO transactionDTO) {
        Transaction transaction =  transactionRepository.findTransactionByBookISBNAndUserId(transactionDTO.getBookISBN(), transactionDTO.getUserId());
        transaction.setReturnDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }


}
