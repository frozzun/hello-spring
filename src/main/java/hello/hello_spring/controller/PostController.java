package hello.hello_spring.controller;

import hello.hello_spring.DTO.CreatePostDto;
import hello.hello_spring.DTO.PostListDto;
import hello.hello_spring.domain.Member;
import hello.hello_spring.domain.Post;
import hello.hello_spring.service.MemberService;
import hello.hello_spring.service.PostService;

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
@Tag(name = "PostController", description = "practice of crud api")
public class PostController {

  private final MemberService memberService;
  private final PostService postService;

  /**
   * 생성자
   * controller, service 연결
   */
  @Autowired
  public PostController(MemberService memberService, PostService postService) {
    this.memberService = memberService;
    this.postService = postService;
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
      description = "Post data to create",
      required = true,
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = CreatePostDto.class)
      )
    )
  )
  public void postCreate(@org.springframework.web.bind.annotation.RequestBody CreatePostDto createPostDto) {

    // 디버깅 로그 추가
    System.out.println("CreatePostDto received: name = " + createPostDto.getName() + ", post = " + createPostDto.getPost());

    // 매핑 확인
    if (createPostDto.getName() == null || createPostDto.getPost() == null || createPostDto.getMember_id() == null) {
      throw new IllegalArgumentException("Invalid input data: Name or Post is null");
    }



    Optional<Member> OPmember = memberService.findById(createPostDto.getMember_id());

    if (OPmember.isPresent()) {
      // member가 존재할 때
      Member member = OPmember.get();
      Post post = new Post(member, createPostDto.getName(), createPostDto.getPost());
      postService.create(post);
    } else {
      throw new IllegalArgumentException("Member with ID " + createPostDto.getMember_id() + " not found");
    }
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

  public List<PostListDto> list(Model model) {
    List<Post> posts = postService.read();

    //Post 객체를 PostListDto 객체로 변환
    List<PostListDto> postsDto = posts.stream().map(post -> {
      PostListDto dto = new PostListDto();
      dto.setPostId(post.getId());
      dto.setTitle(post.getName());
      dto.setContent(post.getPost());
      dto.setMemberId(post.getMember().getId()); // memberId 설정
      return dto;
    }).toList();

    model.addAttribute("members", postsDto); // 모델에 DTO 추가
    return postsDto; // DTO 리스트 반환
  }

//  /**
//   * Update
//   */
//  @PatchMapping("/posts/updates")
//  @ResponseBody
//  public ResponseEntity<String> update(@RequestBody(
//      description = "Post data to update",
//      required = true,
//      content = @Content(
//          mediaType = "application/json",
//          schema = @Schema(implementation = CreatePostDto.class)
//      )
//  ) Post member) {
//    Optional<Post> memberOptional = memberService.findMember(id);
//    if (memberOptional.isPresent()) {
//      Post member = memberOptional.get();
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
    Optional<Post> optionalMember = postService.findPost(id);

    if (optionalMember.isPresent()) {
      Post post = optionalMember.get();
      postService.delete(post);
      return ResponseEntity.ok("Post deleted successfully");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
    }
  }
}



