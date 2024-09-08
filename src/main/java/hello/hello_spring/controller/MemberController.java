package hello.hello_spring.controller;

import hello.hello_spring.DTO.CreateDto;
import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
/**
 * Rest API 를 사용
 * */
//@RestController
@Tag(name = "MemberController", description = "practice of crud api")
public class MemberController {

  private final MemberService memberService;
  private final MemberRepository memberRepository;

  /**
   * 생성자
   * controller, service 연결
   */
  @Autowired
  public MemberController(MemberService memberService, MemberRepository memberRepository) {
    this.memberService = memberService;
    this.memberRepository = memberRepository;
  }

  /**
   * Create
   */
  @PostMapping("/posts/create")
  @ResponseBody
  @Operation(
      summary = "글 작성",
      description = "글을 작성할 때 사용하는 API",
      requestBody = @RequestBody(
      description = "Member data to create",
      required = true,
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = CreateDto.class)
      )
    )
  )
  public void postCreate(@org.springframework.web.bind.annotation.RequestBody CreateDto createDto) {

    // 디버깅 로그 추가
    System.out.println("CreateDto received: name = " + createDto.getName() + ", post = " + createDto.getPost());

    // 매핑 확인
    if (createDto.getName() == null || createDto.getPost() == null) {
      throw new IllegalArgumentException("Invalid input data: Name or Post is null");
    }

    Member member = new Member(createDto.getName(), createDto.getPost());
    memberService.create(member);
  }
  /**
   * Read
   */
  @GetMapping("/posts")
  @ResponseBody
  @Operation(
      summary = "전체 글 조회",
      description = "글을 조회 할 때 사용하는 API"
  )

  public List<Member> list(Model model) {
    List<Member> members = memberService.read();
    model.addAttribute("members", members);
    return members;
  }

//  /**
//   * Update
//   */
//  @PatchMapping("/posts/updates")
//  @ResponseBody
//  public ResponseEntity<String> update(@RequestBody(
//      description = "Member data to update",
//      required = true,
//      content = @Content(
//          mediaType = "application/json",
//          schema = @Schema(implementation = CreateDto.class)
//      )
//  ) Member member) {
//    Optional<Member> memberOptional = memberService.findMember(id);
//    if (memberOptional.isPresent()) {
//      Member member = memberOptional.get();
//      member.setPost(post);
//      return ResponseEntity.ok(memberService.update(member));
//    }
//    else{
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
//  }

  /**
   * Delete
   */
  @DeleteMapping("/posts/delete")
  @ResponseBody
  @Operation(summary = "글 삭제", description = "글을 삭제할 때 사용하는 API")
  @Parameters({
      @Parameter(name = "id", description = "글 id", example = "1")
  })
  public ResponseEntity<String> delete(
      @Parameter(description = "ID of the post to be deleted")
      @RequestParam("id") Long id) {
    Optional<Member> optionalMember = memberService.findMember(id);

    if (optionalMember.isPresent()) {
      Member member = optionalMember.get();
      memberService.delete(member);
      return ResponseEntity.ok("Post deleted successfully");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
    }
  }
}



