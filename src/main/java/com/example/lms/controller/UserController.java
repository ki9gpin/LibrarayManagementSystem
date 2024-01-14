package com.example.lms.controller;

import com.example.lms.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("")
    public List<User> getAllUsers(){
        return null;
    }
    @GetMapping("/{id}")
    public User getUserByISBN(@PathVariable String id){
        return null;
    }
    @PostMapping("")
    public User createUserEntry(@RequestBody User user){
        //bookService.createUserEntry(book)
        return null;
    }

    @PutMapping("/{id}")
    public User updateUserEntry(@RequestBody User user, @PathVariable String id ){
        // bookService.updateBookEntry(isbn,user);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBookEntryByISBN(@PathVariable String id){
        // bookService.deleteBookEntry(isbn);
    }
}
