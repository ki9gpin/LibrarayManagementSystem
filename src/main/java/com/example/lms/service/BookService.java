package com.example.lms.service;

import com.example.lms.entity.Book;
import com.example.lms.error.BookNotFoundException;
import com.example.lms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return bookRepository.findBookByISBN(isbn);
    }

    public Book createBookEntry(Book book) {
       return bookRepository.save(book);
    }

    public Book updateBookEntry(String isbn, Book book) throws BookNotFoundException {
        Optional<Book> optBook = bookRepository.findBookByISBN(isbn);
        if(optBook.isPresent()){
            Book storedBook=optBook.get();
            storedBook.setTitle(book.getTitle());
            storedBook.setAuthor(book.getAuthor());
            storedBook.setGenre(book.getGenre());
            storedBook.setPublisher(book.getPublisher());
            storedBook.setAvailableCopies(book.getAvailableCopies());
            return bookRepository.save(storedBook);
        }else{
            throw new BookNotFoundException("No book found for the provided ISBN");
        }
    }

    public HttpStatus updateBookAvailableCopiesCount(String isbn, long count) throws Exception {
        Optional<Book> optbook = bookRepository.findBookByISBN(isbn);
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

    public HttpStatus deleteBookEntry(String isbn) {
        return bookRepository.deleteByISBN(isbn) ? HttpStatus.OK: HttpStatus.NOT_FOUND;
    }
}
