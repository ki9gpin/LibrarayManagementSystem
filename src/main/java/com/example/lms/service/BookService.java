package com.example.lms.service;

import com.example.lms.entity.Book;
import com.example.lms.error.BookNotFoundException;
import com.example.lms.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByISBN(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    public Book createBookEntry(Book book) {
       return bookRepository.save(book);
    }

    public Book updateBookEntry(String isbn, Book book) throws BookNotFoundException {
        Book bookToSave = bookRepository.findBookByIsbn(isbn).orElseThrow(()-> new BookNotFoundException("Book not found for the provided isbn"));
            bookToSave.setTitle(book.getTitle());
            bookToSave.setAuthor(book.getAuthor());
            bookToSave.setGenre(book.getGenre());
            bookToSave.setPublisher(book.getPublisher());
            bookToSave.setYear(book.getYear());
            bookToSave.setAvailableCopies(book.getAvailableCopies());
            return bookRepository.save(bookToSave);
    }

    @Transactional
    public void deleteBookEntry(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Transactional
    public void decreaseAvailableCount(Book book) {
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
    }


    public List<Book> getBooksByISBN(List<String> booksISBN) {
        return bookRepository.findAllByIsbnIn(booksISBN);
    }

    @Transactional
    public void increaseAvailableCount(String isbn) throws BookNotFoundException {
        Book book = bookRepository.findBookByIsbn(isbn).orElseThrow(()-> new BookNotFoundException("Book not found for the provided isbn"));
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);
    }
}
