package com.example.lms.repository;

import com.example.lms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findBookByIsbn(String isbn);
    public void deleteByIsbn(String isbn);

    List<Book> findAllByIsbnIn(List<String> booksISBN);
}
