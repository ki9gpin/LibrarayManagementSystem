package com.example.lms.controller;

import com.example.lms.entity.Book;
import com.example.lms.error.BookNotFoundException;
import com.example.lms.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;
    private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @GetMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{isbn}")
    public Optional<Book> getBookByISBN(@PathVariable long isbn){
        return bookService.getBookByISBN(isbn);
    }
    @PostMapping("")
    public Book createBookEntry(@RequestBody Book book){
        System.out.println("inside create book entry ; title = "+book.getISBN());
        return bookService.createBookEntry(book);
    }

    @PutMapping("/{isbn}")
    public Book updateBookEntry(@PathVariable Book book, @PathVariable String isbn ) throws BookNotFoundException {
        return bookService.updateBookEntry(isbn,book);
    }

    @DeleteMapping("/{isbn}")
    public HttpStatus deleteBookEntryByISBN(@PathVariable String isbn){
          return bookService.deleteBookEntry(isbn);
    }

}
