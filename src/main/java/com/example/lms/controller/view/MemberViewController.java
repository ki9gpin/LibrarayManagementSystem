package com.example.lms.controller.view;

import com.example.lms.entity.Member;
import com.example.lms.entity.Transaction;
import com.example.lms.error.MemberNotFoundException;
import com.example.lms.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class MemberViewController {
    final MemberService memberService;

    public MemberViewController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String getAllMembers(Model model){
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members",members);
        return "members";
    }

    @GetMapping("/members/{ID}")
    public String getMemberByID(Model model, @PathVariable long ID) throws MemberNotFoundException {
        Optional<Member> optMember = memberService.getMemberById(ID);
        if (optMember.isPresent()) {
            model.addAttribute("member",optMember.get());
        }else {
            throw new MemberNotFoundException("Member not found.");
        }
        return "member";
    }

    @GetMapping("/members/{ID}/transactions")
    public String getTransactionsByMemberID(Model model, @PathVariable long ID)  {
        List<Transaction> transactions = memberService.getTransactionsByMemberId(ID);
        model.addAttribute("transactions",transactions);
        return "member";
    }

    @GetMapping("/add-member")
    public String getAddBook(Model model){

        model.addAttribute("member", new Member());
        return "add-member";
    }

    @PostMapping("/add-member")
    public String addMember(@ModelAttribute("member") Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "add-member";
        }
        memberService.createMemberEntry(member);
        return "redirect:/members";
    }
    @GetMapping("/update-member/{userid}")
    public ModelAndView getUpdateMemberView(Model model, @PathVariable long userid) throws MemberNotFoundException {
        Optional<Member> member = memberService.getMemberById(userid);
        if(member.isPresent()){
            ModelAndView  updateView = new ModelAndView("update-member");
            updateView.addObject("member",member.get());
            updateView.addObject("update",true);
            return updateView;
        } else{
            throw  new MemberNotFoundException("Couldn't find the member");
        }

    }

    @PostMapping("/update-member/{userid}")
    public String updateMemberEntry(@ModelAttribute("member") Member member, @PathVariable long userid ) throws MemberNotFoundException {
        memberService.updateMemberEntry(userid,member);
        return "redirect:/members/"+member.getId();
    }

    @GetMapping("/delete-member/{userid}")
    public String deleteMemberEntry(@PathVariable long userid ) {
        memberService.deleteMemberEntry(userid);
        return "redirect:/members";
    }

}
