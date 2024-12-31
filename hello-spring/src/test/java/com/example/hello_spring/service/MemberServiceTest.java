package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void join() {
//        given(주어진 상황) when(검증) then(결과) 문법 추천
//        given
        Member member = new Member();
        member.setName("james");

//        when
        Long saveId = memberService.join(member);

//        then
//        member
//        Assertions.assertThat()
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}