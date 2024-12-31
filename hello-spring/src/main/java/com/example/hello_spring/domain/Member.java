package com.example.hello_spring.domain;

public class Member {
//    member 요구사항 : id와 식별자
    private Long id; //시스템이 정하는 id값
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
