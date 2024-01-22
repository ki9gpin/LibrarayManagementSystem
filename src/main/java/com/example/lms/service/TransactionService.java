package com.example.lms.service;

import com.example.lms.dto.TransactionDTO;
import com.example.lms.entity.Book;
import com.example.lms.entity.Member;
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

    public List<Transaction> getTransactionByUserId(long id) {
        return transactionRepository.findTransactionByUserId(id);
    }

    public List<Transaction> getTransactionByBookISBN(String isbn) {
        return transactionRepository.findTransactionByBookISBN(isbn);
    }

    public Transaction checkOut(TransactionDTO transactionDTO, Member member, Book book) {
            Transaction transaction = new Transaction();
            transaction.setBookISBN(transactionDTO.getBookISBN());
            transaction.setUserId(transactionDTO.getUserId());
            transaction.setCheckedOutDate(LocalDateTime.now());
            transaction.setReturnDate(null);
            transaction.setMember(member);
            transaction.setBook(book);
            return transactionRepository.save(transaction);
    }

    public Transaction returnBook(TransactionDTO transactionDTO) {
        Transaction transaction =  transactionRepository.findTransactionByBookISBNAndUserId(transactionDTO.getBookISBN(), transactionDTO.getUserId());
        transaction.setReturnDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(long id) {
        return transactionRepository.findById(id);
    }

    public Member getMemberById(long id) {
        return transactionRepository.findMemberById(id);
    }

    public Book getBookById(long id) {
        return transactionRepository.findBookById(id);
    }
}
