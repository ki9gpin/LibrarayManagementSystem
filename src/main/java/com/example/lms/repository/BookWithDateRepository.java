package com.example.lms.repository;

import com.example.lms.entity.Book;
import com.example.lms.entity.BookWithDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookWithDateRepository extends JpaRepository<BookWithDate,Long> {

    List<BookWithDate> findAllByBook(Book book);
}
