package com.example.lms.controller.api;

import com.example.lms.dto.TransactionDTO;
import com.example.lms.entity.Book;
import com.example.lms.entity.Member;
import com.example.lms.entity.Transaction;
import com.example.lms.service.BookService;
import com.example.lms.service.MemberService;
import com.example.lms.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    final TransactionService transactionService;
    final MemberService memberService;
    final BookService bookService;

    public TransactionController(TransactionService transactionService, Transaction transaction, MemberService memberService, BookService bookService) {
        this.transactionService = transactionService;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();

    }
//    @GetMapping("/user/{userId}")
//    public List<Transaction> getTransactionByUserId(@PathVariable long userId){
//        return transactionService.getTransactionByUserId(userId);
//    }

    @GetMapping("/book/{isbn}")
    public List<Transaction> getTransactionByBookISBN(@PathVariable String isbn){
        return transactionService.getTransactionByBookISBN(isbn);
    }

//    @PostMapping("/check-out")
//    public Transaction checkOutBook(@RequestBody TransactionDTO transactionDTO){
//        System.out.println("isbn = "+transactionDTO.getBookISBN() +" userId = "+transactionDTO.getUserId());
//        Member member= memberService.getMemberById(transactionDTO.getUserId()).get();
//        Book book = bookService.getBookByISBN(transactionDTO.getBookISBN()).get();
//        return transactionService.checkOut(transactionDTO, member, book);
//    }

//    @PutMapping("/return")
//    public Transaction returnBook(@RequestBody TransactionDTO transactionDTO){
//        return transactionService.returnBook(transactionDTO);
//    }

}
