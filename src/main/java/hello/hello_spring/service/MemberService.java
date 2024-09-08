package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

  private final MemberRepository memberRepository;

  /**
   * 생성자
   */
  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * Find Member
   */
  public Optional<Member> findMember(Long id) {
    return memberRepository.findById(id);
  }



  /**
   * Create(생성)
   * Post
   */
  public void create(Member member) {
    memberRepository.save(member);
  }

  /**
   * Read(조회)
   * Get
   */
  public List<Member> read() {
    return memberRepository.findAll();
  }

  /**
   * Update(수정)
   * Patch
   */
  public String update(Member member) {
    memberRepository.save(member);
    return member.getPost();
  }

  /**
   * Delete(삭제)
   * Delete
   */
  public void delete(Member member) {
    memberRepository.delete(member);
  }


}
