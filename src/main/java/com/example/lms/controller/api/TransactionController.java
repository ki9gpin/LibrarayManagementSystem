package com.example.lms.controller.api;

import com.example.lms.dto.TransactionDTO;
import com.example.lms.entity.Transaction;
import com.example.lms.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService, Transaction transaction) {
        this.transactionService = transactionService;
    }

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
    public Transaction checkOutBook(@RequestBody TransactionDTO transactionDTO){
        System.out.println("isbn = "+transactionDTO.getBookISBN() +" userId = "+transactionDTO.getUserId());
        return transactionService.checkOut(transactionDTO);
    }

    @PutMapping("/return")
    public Transaction returnBook(@RequestBody TransactionDTO transactionDTO){
        return transactionService.returnBook(transactionDTO);
    }

}
