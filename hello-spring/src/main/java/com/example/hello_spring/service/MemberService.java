package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

//    회원 레파지토리
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
