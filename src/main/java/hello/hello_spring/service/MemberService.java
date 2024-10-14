package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

  private final MemberRepository memberRepository;

  /**
   * 생성자 생성
   */
  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원가입
   * @param member
   */
  public void create(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
  }

  /**
   * 로그인
   */
  public void login(String email, String password) {
    memberRepository.findByEmail(email).ifPresent(member -> {});
  }

  /**
   * 로그아웃
   */


  /**
   * Find By I'd
   */
  public Optional<Member> findById(Long member_id) {
    return memberRepository.findById(member_id);
  }

  //중복 회원 검증
  private void validateDuplicateMember(Member member) {   //중복 검증
    memberRepository.findByEmail(member.getEmail())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }
}
