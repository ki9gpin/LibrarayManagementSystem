package com.example.lms.service;

import com.example.lms.entity.Book;
import com.example.lms.entity.BookWithDate;
import com.example.lms.entity.Member;
import com.example.lms.entity.TransactionMultiple;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.TransactionRepositoryMultiple;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceMultiple {


    private static long tempTransacionId;
    private final TransactionRepositoryMultiple transactionRepositoryMultiple;
    private final BookRepository bookRepository;
    public TransactionServiceMultiple(TransactionRepositoryMultiple transactionRepositoryMultiple, BookRepository bookRepository) {
        this.transactionRepositoryMultiple = transactionRepositoryMultiple;
        this.bookRepository = bookRepository;
    }

    public TransactionMultiple checkOut(TransactionMultiple transactionMultiple, Member member, List<BookWithDate> books) {
        return transactionRepositoryMultiple.save(transactionMultiple);
    }

    public List<TransactionMultiple> getTransactionsByUserId(long id) {
        List<TransactionMultiple> transactionsMultiple= transactionRepositoryMultiple.findAllByUserId(id);
        return transactionsMultiple;
    }

    public List<TransactionMultiple> getAllTransactions() {
        return transactionRepositoryMultiple.findAll();
    }

    public Optional<TransactionMultiple> getTransactionById(long id) {

        return transactionRepositoryMultiple.findById(id);
    }

    public List<BookWithDate> createBooksWithDate(List<Book> booksByISBN, LocalDate checkedOutDate) {
        List<BookWithDate> booksWithDate = new ArrayList<>();

        for(Book book :  booksByISBN){
            BookWithDate bookWithDate = new BookWithDate(book,checkedOutDate,null);
            booksWithDate.add(bookWithDate);
        }
        return  booksWithDate;
    }

    public void returnBookView(TransactionMultiple transactionMultiple, String isbn) {
        List<BookWithDate> booksWithDate = transactionMultiple.getBooksWithDate();
        for(BookWithDate bookWithDate : booksWithDate){
            if(bookWithDate.getBook().getIsbn().equals(isbn)){
                bookWithDate.setReturnDate(LocalDate.now());
            }
        }
        transactionMultiple.setBooksWithDate(booksWithDate);
        transactionRepositoryMultiple.save(transactionMultiple);
    }

    public static long getTempTransacionId() {
        return tempTransacionId;
    }

    public static void setTempTransacionId(long tempTransacionId) {
        TransactionServiceMultiple.tempTransacionId = tempTransacionId;
    }

    public List<TransactionMultiple> getTransactionByBookISBN(String isbn) {

        return transactionRepositoryMultiple.findAllByBook(bookRepository.findBookByIsbn(isbn).get());
    }
}
