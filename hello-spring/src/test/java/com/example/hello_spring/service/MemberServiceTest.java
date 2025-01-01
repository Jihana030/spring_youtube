package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
//         메소드 끝날 때마다 동작
//         올바른 test를 위해 필요함
        memberRepository.clearStore();
    }

    @Test
    void join() {
//        given(주어진 상황) when(검증) then(결과) 문법 추천
//        given
        Member member = new Member();
        member.setName("spring");

//        when
        Long saveId = memberService.join(member);

//        then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test void memDuplicationException() {
//        given
        Member member1 = new Member();
        member1.setName("james");

        Member member2 = new Member();
        member2.setName("james");
//        when
        memberService.join(member1);
//        try/catch는 넣기에 좀 애매한 부분이 있음.
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}