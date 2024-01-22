package com.example.lms.repository;

import com.example.lms.entity.Member;
import com.example.lms.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
