package com.example.lms.service;

import com.example.lms.entity.Book;
import com.example.lms.entity.BookWithDate;
import com.example.lms.entity.Member;
import com.example.lms.entity.Transaction;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.BookWithDateRepository;
import com.example.lms.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final BookWithDateRepository bookWithDateRepository;
    public TransactionService(TransactionRepository transactionRepository, BookRepository bookRepository, BookWithDateRepository bookWithDateRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.bookWithDateRepository = bookWithDateRepository;
    }

    public Transaction checkOut(Transaction transaction, Member member, List<BookWithDate> books) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUserId(long id) {
        return transactionRepository.findAllByUserId(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(long id) {

        return transactionRepository.findById(id);
    }

    public List<BookWithDate> createBooksWithDate(List<Book> booksByISBN, LocalDate checkedOutDate) {
        List<BookWithDate> booksWithDate = new ArrayList<>();

        for(Book book :  booksByISBN){
            BookWithDate bookWithDate = new BookWithDate(book,checkedOutDate,null);
            booksWithDate.add(bookWithDate);
        }
        return  booksWithDate;
    }

    public void returnBookView(Transaction transaction, String isbn) {
        List<BookWithDate> booksWithDate = transaction.getBooksWithDate();
        for(BookWithDate bookWithDate : booksWithDate){
            if(bookWithDate.getBook().getIsbn().equals(isbn)){
                bookWithDate.setReturnDate(LocalDate.now());
            }
        }
        transaction.setBooksWithDate(booksWithDate);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionByBookISBN(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn).orElse(null);
        List<BookWithDate> booksWithDate = bookWithDateRepository.findAllByBook(book);
        return transactionRepository.findAllByBooksWithDateIn(booksWithDate);
    }
}
