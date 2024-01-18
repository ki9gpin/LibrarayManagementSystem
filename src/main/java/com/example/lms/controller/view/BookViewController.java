package com.example.lms.controller.view;

import com.example.lms.controller.api.BookController;
import com.example.lms.entity.Book;
import com.example.lms.error.BookNotFoundException;
import com.example.lms.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class BookViewController {

    final BookService bookService;
    private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model){
        List<Book> books =  bookService.getAllBooks();
        model.addAttribute("books",books);
        return "books";
    }

    @GetMapping("/books/{isbn}")
    public String getBookByISBN(Model model, @PathVariable String isbn) throws BookNotFoundException {
        Optional<Book> book = bookService.getBookByISBN(isbn);
        if (book.isPresent()){
//            book.get().setYear(book.get().getYear().\\\);
            model.addAttribute("book", book.get());
        } else {
            throw new BookNotFoundException("Book not available");
        }
        return "book";
    }

    @GetMapping("/add-book")
    public String getAddBook(Model model){

        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "add-book";
        }
        bookService.createBookEntry(book);
        return "redirect:/books";
    }
    @GetMapping("/update-book/{isbn}")
    public String getUpdateBookView( Model model, @PathVariable String isbn) throws BookNotFoundException {
        Optional<Book> book = bookService.getBookByISBN(isbn);
        if(book.isPresent()){
            model.addAttribute("book",book.get());
            model.addAttribute("update",true);
            return "update-book";
        } else{
            throw  new BookNotFoundException("Couldn't find the book");
        }

    }

    @PutMapping("/update-book/{isbn}")
    public String updateBookEntry(@ModelAttribute("book") Book book, @PathVariable String isbn ) throws BookNotFoundException {
        bookService.updateBookEntry(isbn,book);
        return "redirect:/books/"+book.getISBN();
    }

    @DeleteMapping("/delete-book/{isbn}")
    public String deleteBookEntry(@PathVariable String isbn ) {
        bookService.deleteBookEntry(isbn);
        return "redirect:/books/";
    }

}
