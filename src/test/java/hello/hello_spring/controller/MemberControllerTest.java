package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {

  MemberService memberService;
  MemberRepository memberRepository;


  @BeforeEach
  public void beforeEach() {
    memberService = new MemberService(memberRepository);
  }

  @Test
  void postCreate() {
    //Given
    Member member1 = new Member();
    member1.setId(1L);
    member1.setPost("hello create of CRUD, My name is Frozzun!!");

    //When



    //Then

  }
}