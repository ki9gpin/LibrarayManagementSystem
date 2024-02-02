package com.example.lms.repository;

import com.example.lms.entity.Book;
import com.example.lms.entity.TransactionMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositoryMultiple extends JpaRepository<TransactionMultiple, Long> {

    List<TransactionMultiple> findAllByUserId(long id);

    List<TransactionMultiple> findAllByBook(Book book);
}
