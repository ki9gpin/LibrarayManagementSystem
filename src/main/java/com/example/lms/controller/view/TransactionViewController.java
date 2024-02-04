package com.example.lms.controller.view;

import com.example.lms.entity.*;
import com.example.lms.error.BookNotFoundException;
import com.example.lms.service.BookService;
import com.example.lms.service.MemberService;
import com.example.lms.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping
public class TransactionViewController {
    final MemberService memberService;
    final BookService bookService;
    final TransactionService transactionService;

    public TransactionViewController( MemberService memberService, BookService bookService, TransactionService transactionService) {
        this.memberService = memberService;
        this.bookService = bookService;
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model){
        var transactions = transactionService.getAllTransactions();

        model.addAttribute("transactions", transactions);
        return "transactions";
    }
    @GetMapping("/transactions/{id}")
    public String getTransactionByID(@PathVariable long id, Model model){
        var transaction = transactionService.getTransactionById(id).orElse(null);
        model.addAttribute("transaction",transaction);
        model.addAttribute("transactionId",id);
        return "transaction";
    }
    @GetMapping("/transactions/user/{id}")
    public String getTransactionByUserID(@PathVariable long id, Model model){
        var transaction =  transactionService.getTransactionsByUserId(id);
        model.addAttribute("transactions",transaction);
        return "transactions";
    }

    @GetMapping("/transactions/book/{isbn}")
    public String getTransactionByBookISBN(@PathVariable String isbn, Model model){
        var transactions = transactionService.getTransactionByBookISBN(isbn);
        model.addAttribute("transactions",transactions);
        return "transactions";
    }

    @GetMapping("/check-out")
    public String checkOutBookView(Model model){
        List<Member> members = memberService.getAllMembers();
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("members", members);
        model.addAttribute("books",books);
        return "check-out";
    }

    @GetMapping("/check-out/{isbn}")
    public String checkOutBookView(@PathVariable String isbn,  Model model){
        List<Member> members = memberService.getAllMembers();
        List<Book> books = new ArrayList<>();
        books.add(bookService.getBookByISBN(isbn).orElse(null));
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("members", members);
        model.addAttribute("books",books);
        return "check-out";
    }

    @PostMapping("/check-out")
    public String checkOutBook(Transaction transaction, Model model){
        Member member= memberService.getMemberById(transaction.getUserId()).orElse(null);
        List<String> booksISBN = transaction.getBooksIsbn();
        List<BookWithDate> books = transactionService.createBooksWithDate( bookService.getBooksByISBN(booksISBN), transaction.getCheckedOutDate());
        transaction.setBooksWithDate(books);
        transaction.setMember(member);
        var transactionChecked =  transactionService.checkOut(transaction,member,books);
        for(BookWithDate book : books){
            bookService.decreaseAvailableCount(book.getBook());
        }
        memberService.increaseCheckOutCount(Objects.requireNonNull(member),books.size());
        model.addAttribute("transaction",transactionChecked);
        return "redirect:/transactions/user/"+transactionChecked.getUserId();
    }

    @PostMapping("/return/{isbn}")
    public String returnBook(@PathVariable String isbn,  @RequestParam("transactionId") long transactionId,  Model model) throws BookNotFoundException {
        Transaction transaction = transactionService.getTransactionById(transactionId).orElse(null);
        transactionService.returnBookView(Objects.requireNonNull(transaction),isbn);
        bookService.increaseAvailableCount(isbn);
        memberService.decreaseCheckOutCount(transaction.getMember());
        model.addAttribute("transaction", transaction);
        return "redirect:/transactions/"+ transaction.getId();
    }
}
