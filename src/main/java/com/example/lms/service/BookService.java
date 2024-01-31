package com.example.lms.service;

import com.example.lms.entity.Book;
import com.example.lms.error.BookNotFoundException;
import com.example.lms.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        Optional<Book> optBook = bookRepository.findBookByIsbn(isbn);
        System.out.println("inside update book entry");
        if(optBook.isPresent()){
            System.out.println("Updating book. Book is available.");
//            Book storedBook=optBook.get();
            optBook.get().setTitle(book.getTitle());
            optBook.get().setAuthor(book.getAuthor());
            optBook.get().setGenre(book.getGenre());
            optBook.get().setPublisher(book.getPublisher());
            optBook.get().setYear(book.getYear());
            optBook.get().setAvailableCopies(book.getAvailableCopies());
            return bookRepository.save(optBook.get());
        }else{
            throw new BookNotFoundException("No book found for the provided ISBN");
        }
    }

    public HttpStatus updateBookAvailableCopiesCount(String isbn, long count) throws Exception {
        Optional<Book> optbook = bookRepository.findBookByIsbn(isbn);
        if(optbook.isPresent() ){
            Book storedBook = optbook.get();
            if(count>=0){
                storedBook.setAvailableCopies(count);
                bookRepository.save(storedBook);
                return HttpStatus.OK;
            }else{
                throw new IllegalArgumentException("AvailableCopies must be positive");
            }
        }else{
            throw new BookNotFoundException("No book found for the provided ISBN");
        }
    }

    @Transactional
    public void deleteBookEntry(String isbn) {
        bookRepository.deleteByIsbn(isbn);  // ? HttpStatus.OK: HttpStatus.NOT_FOUND;
    }

    @Transactional
    public void decreaseAvailableCount(Book book) {
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
    }

    @Transactional
    public void increaseAvailableCount(Book book) {
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);
    }

    public List<Book> getBooksByISBN(List<String> booksISBN) {
        return bookRepository.findAllByIsbnIn(booksISBN);
    }
}
