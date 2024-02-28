package com.example.lms.service;


import com.example.lms.entity.Member;
import com.example.lms.entity.Transaction;
import com.example.lms.error.MemberNotFoundException;
import com.example.lms.repository.MemberRepository;
import com.example.lms.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    final MemberRepository memberRepository;
    final TransactionRepository transactionRepository;

    public MemberService(MemberRepository memberRepository, TransactionRepository transactionRepository) {
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
    }


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    public Page<Member> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public Optional<Member> getMemberById(long id) {
        return memberRepository.findById(id);
    }

    public void createMemberEntry(Member member) {
         memberRepository.save(member);
    }

    public void updateMemberEntry(long id, Member member) throws MemberNotFoundException {
        Member storedMember = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("Member not found"));
        storedMember.setFirstName(member.getFirstName());
        storedMember.setLastName(member.getLastName());
        storedMember.setEmail(member.getEmail());
        storedMember.setBooksCheckedOut(member.getBooksCheckedOut());
        memberRepository.save(storedMember);
    }

    public void deleteMemberEntry(long id) {
        memberRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByUserId(long id) {
        return transactionRepository.findAllByUserId(id);
    }

    @Transactional
    public void increaseCheckOutCount(Member member,int numberOfBooks) {
        member.setBooksCheckedOut(member.getBooksCheckedOut()+numberOfBooks);
        memberRepository.save(member);
    }

    public void decreaseCheckOutCount(Member member) {
        member.setBooksCheckedOut(member.getBooksCheckedOut()-1);
        memberRepository.save(member);
    }
}
