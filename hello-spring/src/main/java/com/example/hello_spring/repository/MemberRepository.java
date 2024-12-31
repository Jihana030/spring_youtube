package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장
//    optional은 null이 반환될때 감싸서 반환해줌
    Optional<Member> findById(Long id); //회원 id 찾기
    Optional<Member> findByName(String name); //이름 찾기
    List<Member> findAll(); //지금까지 저장된 모든 회원 리스트 반환
}
