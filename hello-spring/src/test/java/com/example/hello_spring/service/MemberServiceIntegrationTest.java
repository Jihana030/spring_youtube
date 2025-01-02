package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//스프링으로 테스트 할 때 골벵이 2개 써주면 됨.
@SpringBootTest
@Transactional //얘가 있으면 afterEach 없어도 됨. db에 트랜잭션을 해줘서 테스트에 사용한 데이터는 알아서 지워줌. 롤백시켜줌. 그래서 테스트를 반복할 수 있게 해줌.
//    테스트 케이스에 붙었을때만 이렇게 동작함.
//    단위테스트가 잘 되는게 좋은 테스트. 여기 실행되는건 통합 테스트.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        then
    }

}