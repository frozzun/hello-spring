package hello.hello_spring.controller;

import hello.hello_spring.DTO.CreateMemberDto;
import hello.hello_spring.DTO.CreatePostDto;
import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

  private final MemberService memberService;

  /**
   * 생성자
   * controller, service 연결
   */
  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }


  @PostMapping("/member/create")
  @ResponseBody
  @Operation(
      summary = "회원 가입",
      description = "회원가입 API",
      requestBody = @RequestBody(
          description = "Member create",
          required = true,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = CreateMemberDto.class)
          )
      )
  )
  public Long create(@org.springframework.web.bind.annotation.RequestBody CreateMemberDto createMemberDto) {
    // 디버깅 로그 추가
    System.out.println("CreatePostDto received: name = " + createMemberDto.getEmail() + ", post = " + createMemberDto.getPassword());

    // 매핑 확인
    if (createMemberDto.getEmail() == null || createMemberDto.getPassword() == null) {
      throw new IllegalArgumentException("Invalid input data: Name or Post is null");
    }

    Member member = new Member(createMemberDto.getEmail(), createMemberDto.getPassword());
    memberService.create(member);

    return member.getId();
  }
}
