package com.example.lms.controller.view;

import com.example.lms.dto.TransactionDTO;
import com.example.lms.entity.*;
import com.example.lms.service.BookService;
import com.example.lms.service.MemberService;
import com.example.lms.service.TransactionService;
import com.example.lms.service.TransactionServiceMultiple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class TransactionViewController {
    final TransactionService transactionService;
    final MemberService memberService;
    final BookService bookService;
    final TransactionServiceMultiple transactionServiceMultiple;

    public TransactionViewController(TransactionService transactionService, MemberService memberService, BookService bookService, TransactionServiceMultiple transactionServiceMultiple) {
        this.transactionService = transactionService;
        this.memberService = memberService;
        this.bookService = bookService;
        this.transactionServiceMultiple = transactionServiceMultiple;
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model){
        var transactionsMultiple = transactionServiceMultiple.getAllTransactions();

        for(var transaction : transactionsMultiple){
            System.out.println(transaction.getMember().getFirstName());
        }
        model.addAttribute("transactionsMultiple", transactionsMultiple);
        return "transactions";
    }
    @GetMapping("/transactions/{id}")
    public String getTransactionByID(@PathVariable long id, Model model){
        var transactionMultiple = transactionServiceMultiple.getTransactionById(id).get();
        model.addAttribute("transactionMultiple",transactionMultiple);
        model.addAttribute("",transactionMultiple.getId());
        TransactionServiceMultiple.setTempTransacionId(id);
//        model.addAttribute("member",transaction.get().getMember());
//        model.addAttribute("books",transaction.get().getBooks());
        return "transaction";
    }
    @GetMapping("/transactions/user/{id}")
    public String getTransactionByUserID(@PathVariable long id, Model model){
        var transactionMultiple =  transactionServiceMultiple.getTransactionsByUserId(id);
//        List<BookWithDate> books = transactionMultiple.;
        model.addAttribute("transactionsMultiple",transactionMultiple);
        return "transactions";
    }

    @GetMapping("/transactions/book/{isbn}")
    public String getTransactionByBookISBN(@PathVariable String isbn, Model model){
        var transactions = transactionServiceMultiple.getTransactionByBookISBN(isbn);
        model.addAttribute("transactions",transactions);
        return "transactions";
    }

    @GetMapping("/check-out-multiple")
    public String checkOutBookView(Model model){
        List<Member> members = memberService.getAllMembers();
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("transactionMultiple", new TransactionMultiple());
        model.addAttribute("members", members);
        model.addAttribute("books",books);
        return "check-out-multiple";
    }

    @PostMapping("/check-out-multiple")
    public String checkOutMultipleBook( TransactionMultiple transactionMultiple, Model model){
        Member member= memberService.getMemberById(transactionMultiple.getUserId()).get();
        List<String> booksISBN = transactionMultiple.getBooksIsbn();
        List<BookWithDate> books = transactionServiceMultiple.createBooksWithDate( bookService.getBooksByISBN(booksISBN), transactionMultiple.getCheckedOutDate());
        transactionMultiple.setBooksWithDate(books);
        transactionMultiple.setMember(member);
        var transaction =  transactionServiceMultiple.checkOut(transactionMultiple,member,books);
        for(BookWithDate book : books){
            bookService.decreaseAvailableCount(book.getBook());
        }
        memberService.increaseCheckOutCount(member);
        model.addAttribute("transaction",transaction);
        return "redirect:/transactions/user/"+transaction.getUserId();
    }

    @GetMapping("/check-out/{isbn}")
    public String checkOutBookByISBNView(@PathVariable String isbn, Model model){
        List<Member> members = memberService.getAllMembers();
        Book book = bookService.getBookByISBN(isbn).get();
        List<Book> books = new ArrayList<>();
        books.add(book);
        model.addAttribute("transactionMultiple", new TransactionMultiple());
        model.addAttribute("members", members);
        model.addAttribute("books",books);
        return "check-out-multiple";
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

    @PostMapping("/return/{isbn}")
    public String returnBook(@PathVariable String isbn, Model model){
//        Member member= memberService.getMemberById(transaction.getUserId()).get();

        TransactionMultiple transactionMultiple = transactionServiceMultiple.getTransactionById(TransactionServiceMultiple.getTempTransacionId()).get();
        transactionServiceMultiple.returnBookView(transactionMultiple,isbn);
        bookService.increaseAvailableCount(isbn);
        memberService.decreaseCheckOutCount(transactionMultiple.getMember());
        model.addAttribute("transaction",transactionMultiple);
        return "redirect:/transactions/"+transactionMultiple.getId();
    }
}
