package com.example.lms.repository;

import com.example.lms.entity.TransactionMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositoryMultiple extends JpaRepository<TransactionMultiple, Long> {

}
