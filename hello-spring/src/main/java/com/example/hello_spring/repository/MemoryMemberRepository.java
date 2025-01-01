package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
//    원래 hashMap과 sequence는 동시성 문제 때문에 추가로 붙여써줘야할게 있음. 여기선 그냥 간단히 하는 것.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //sequence는 0,1,2... 이런 식으로 key값을 생성해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        //findAny는 하나라도 찾는 것. 옵셔널로 반환 됨.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values == member
    }

    public void clearStore() {
        store.clear();
    }
}

