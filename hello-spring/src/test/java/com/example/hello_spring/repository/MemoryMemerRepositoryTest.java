package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
     MemoryMemberRepository repository = new MemoryMemberRepository();

//     test는 순서와 관계없이 서로 의존관계 없이 설계가 되어야 함.

     @AfterEach
     public void afterEach() {
//         메소드 끝날 때마다 동작
//         올바른 test를 위해 필요함
        repository.clearStore();
     }

     @Test
    public void save(){
         Member member = new Member();
         member.setName("spring");

         repository.save(member);

         Member result = repository.findById(member.getId()).get(); //옵셔널 꺼낼 때 get 쓸 수 있음. 쓰는걸 추천하진 않지만 테스트에선 유용한듯.

//         System.out.println("result:" + (result == member));
//         Assertions.assertEquals(member, result);
         assertThat(member).isEqualTo(result);

     }

     @Test
    public void findByName(){
         Member member1 = new Member();
         member1.setName("spring1");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("spring2");
         repository.save(member2);

         Member result = repository.findByName("spring1").get();
         assertThat(result).isEqualTo(member1);
     }

     @Test
    public void findAll(){
         Member member1 = new Member();
         member1.setName("spring1");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("spring1");
         repository.save(member2);

         List<Member> result = repository.findAll();
         assertThat(result.size()).isEqualTo(2);
     }
}
