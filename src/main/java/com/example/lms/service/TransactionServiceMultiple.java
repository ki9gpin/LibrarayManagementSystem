package com.example.lms.service;

import com.example.lms.entity.Book;
import com.example.lms.entity.Member;
import com.example.lms.entity.TransactionMultiple;
import com.example.lms.repository.TransactionRepository;
import com.example.lms.repository.TransactionRepositoryMultiple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceMultiple {
    private final TransactionRepositoryMultiple transactionRepositoryMultiple;

    public TransactionServiceMultiple(TransactionRepositoryMultiple transactionRepositoryMultiple) {
        this.transactionRepositoryMultiple = transactionRepositoryMultiple;
    }

    public TransactionMultiple checkOut(TransactionMultiple transactionMultiple, Member member, List<Book> books) {
        return transactionRepositoryMultiple.save(transactionMultiple);
    }

}
