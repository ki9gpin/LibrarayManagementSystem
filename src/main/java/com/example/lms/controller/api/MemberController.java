package com.example.lms.controller.api;

import com.example.lms.entity.Member;
import com.example.lms.error.MemberNotFoundException;
import com.example.lms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
    @GetMapping("/{id}")
    public Optional<Member> getMemberById(@PathVariable long id){
        return memberService.getMemberById(id);
    }
    @PostMapping("")
    public Member createMemberEntry(@RequestBody Member member){
        return  memberService.createMemberEntry(member);
    }

    @PutMapping("/{id}")
    public Member updateMemberEntry(@RequestBody Member member, @PathVariable long id ) throws MemberNotFoundException {
        return memberService.updateMemberEntry(id,member);
    }

    @DeleteMapping("/{id}")
    public void deleteBookEntryByISBN(@PathVariable long id){
         memberService.deleteMemberEntry(id);
    }
}
