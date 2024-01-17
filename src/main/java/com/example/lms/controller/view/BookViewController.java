package com.example.lms.controller.view;

import com.example.lms.controller.api.BookController;
import com.example.lms.entity.Book;
import com.example.lms.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        return "book";
    }
}
