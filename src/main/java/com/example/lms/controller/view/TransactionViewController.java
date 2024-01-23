package com.example.lms.controller.view;

import com.example.lms.dto.TransactionDTO;
import com.example.lms.entity.Book;
import com.example.lms.entity.Member;
import com.example.lms.entity.Transaction;
import com.example.lms.service.BookService;
import com.example.lms.service.MemberService;
import com.example.lms.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class TransactionViewController {
    final TransactionService transactionService;
    final MemberService memberService;
    final BookService bookService;

    public TransactionViewController(TransactionService transactionService, MemberService memberService, BookService bookService) {
        this.transactionService = transactionService;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model){
        var transactions = transactionService.getAllTransactions();
        for(var transaction : transactions){
            System.out.println(transaction.getMember().getFirstName());
        }
        model.addAttribute("transactions", transactions);
        return "transactions";
    }
    @GetMapping("/transactions/{id}")
    public String getTransactionByID(@PathVariable long id, Model model){
        var transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction",transaction.get());
        model.addAttribute("member",transaction.get().getMember());
        model.addAttribute("book",transaction.get().getBook());
        return "transaction";
    }
    @GetMapping("/transactions/user/{id}")
    public String getTransactionByUserID(@PathVariable long id, Model model){
        var transactions =  transactionService.getTransactionByUserId(id);
        model.addAttribute("transactions",transactions);
        return "transactions";
    }

    @GetMapping("/transactions/book/{isbn}")
    public String getTransactionByBookISBN(@PathVariable String isbn, Model model){
        var transactions = transactionService.getTransactionByBookISBN(isbn);
        model.addAttribute("transactions",transactions);
        return "transactions";
    }
    @GetMapping("/check-out/{isbn}")
    public String checkOutBookView(@PathVariable String isbn, Model model){
        List<Member> members = memberService.getAllMembers();
        Book book = bookService.getBookByISBN(isbn).get();
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("members", members);
        model.addAttribute("book",book);
        return "check-out";
    }

    @PostMapping("/check-out")
    public String checkOutBook( TransactionDTO transactionDTO, Model model){
        Member member= memberService.getMemberById(transactionDTO.getUserId()).get();
        System.out.println(transactionDTO.getBookISBN());
        Book book = bookService.getBookByISBN(transactionDTO.getBookISBN()).get();
        var transaction =  transactionService.checkOut(transactionDTO,member,book);
        bookService.decreaseAvailableCount(book);
        memberService.increaseCheckOutCount(member);
        model.addAttribute("transaction",transaction);
        return "redirect:/transactions/user/"+transactionDTO.getUserId();
    }

    @PostMapping("/return")
    public String returnBook(@ModelAttribute("transaction") Transaction transaction, Model model){
        Member member= memberService.getMemberById(transaction.getUserId()).get();
        Book book = bookService.getBookByISBN(transaction.getBookISBN()).get();
        transactionService.returnBookView(transaction);
        bookService.increaseAvailableCount(book);
        memberService.decreaseCheckOutCount(member);
        model.addAttribute("transaction",transaction);
        return "redirect:/transactions/user/"+transaction.getUserId();
    }
}
