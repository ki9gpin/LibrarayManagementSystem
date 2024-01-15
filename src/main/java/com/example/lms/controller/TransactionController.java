package com.example.lms.controller;

import com.example.lms.entity.Transaction;
import com.example.lms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    Transaction transaction;

    @GetMapping("")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();

    }
    @GetMapping("/user/{userId}")
    public Optional<Transaction> getTransactionByUserId(@PathVariable long userId){
        return transactionService.getTransactionByUserId(userId);
    }

    @GetMapping("/book/{isbn}")
    public List<Transaction> getTransactionByBookISBN(@PathVariable String isbn){
        return transactionService.getTransactionByBookISBN(isbn);
    }

    @PostMapping("/check-out")
    public Transaction checkOutBook(@RequestBody long userId, @RequestBody String isbn){
        return transactionService.checkOut(transaction,userId,isbn);
    }

    @PutMapping("/return")
    public Transaction returnBook(@RequestBody long userId, @RequestBody String isbn ){
        return transactionService.returnBook(userId,isbn);
    }

}
