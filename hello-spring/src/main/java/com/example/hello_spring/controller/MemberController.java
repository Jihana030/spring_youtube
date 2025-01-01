package com.example.hello_spring.controller;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//    컨테이너는 controller 생성 되면 딱 가지고 있음. 이걸 스프링 빈이 관리된다고 함.
    private final MemberService memberService;

//    @Autowired private MemberService memberService; - 필드 주입
    @Autowired //컨테이너와 서비스/레파지토리 등 연결시켜줄 때 사용(의존관계 주입) - 생성자주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
