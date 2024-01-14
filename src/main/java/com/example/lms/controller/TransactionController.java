package com.example.lms.controller;

import com.example.lms.entity.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @GetMapping("")
    public List<Transaction> getAllTransactions(){
        //transactionService.getAllTransactions();
        return null;
    }
    @GetMapping("/user/{id}")
    public List<Transaction> getTransactionByUserId(@PathVariable String id){
        //transactionService.getTransactionByUserId(id);
        return null;
    }

    @GetMapping("/book/{isbn}")
    public List<Transaction> getTransactionByBookISBN(@PathVariable String isbn){
        //transactionService.getTransactionByBookISBN(isbn);
        return null;
    }

    @PostMapping("/check-out")
    public Transaction checkOutBook(@RequestBody long userId, @RequestBody String isbn){
        //transactionService.checkOut(userId,isbn);
        return null;
    }

    @PutMapping("/return")
    public Transaction returnBook(@RequestBody long userId, @RequestBody String isbn ){
        // transactionService.return(userId,isbn);
        return null;
    }

}
