package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //컨테이너에게 관리할 녀석이라고 알려줘야함. 없으면 순수한 java일 뿐..
//빈 등록 방식 : @는 컴포넌트 스캔 방식
//웬만하면 빈으로 등록해서 써야 함. 기본 싱글톤 등록(유일무이)
public class MemberService {

//    회원 레파지토리
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    회원 가입
    public long join(Member member) {
//        동명이인은 안됨
//        Optional<Member> result = memberRepository.findByName(member.getName()); //동명이인이 있는지 검색
//        result.ifPresent(m -> { //값을 가지고 있으면 그 값(m)을 반환, 없으면 넘어감
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
//        위 코드를 간단하게
        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId(); //id 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //동명이인이 있는지 검색
            .ifPresent(m -> { //값을 가지고 있으면 그 값(m)을 반환, 없으면 넘어감
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

//    전체회원조회
    public List<Member> findMembers(){
//        service 클래스는 비즈니스에 가까운 용어를 써야함. 그래서 여기는 find고 repository는 get, set으로 쓰는 것.
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
