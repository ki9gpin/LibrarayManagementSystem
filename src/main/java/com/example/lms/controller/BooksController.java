package com.example.lms.controller;

import com.example.lms.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @GetMapping("")
    public List<Book> getAllBooks(){
        return null;
    }
    @GetMapping("/{isbn}")
    public Book getBookByISBN(@PathVariable String isbn){
        return null;
    }
    @PostMapping("")
    public Book createBookEntry(@PathVariable Book book){
        //bookService.createBookEntry(book)
        return null;
    }

    @PutMapping("/{isbn}")
    public Book updateBookEntry(@PathVariable Book book, @PathVariable String isbn ){
        // bookService.updateBookEntry(isbn,book);
        return null;
    }

    @DeleteMapping("/{isbn}")
    public void deleteBookEntryByISBN(@PathVariable String isbn){
        // bookService.deleteBookEntry(isbn);
    }

}
