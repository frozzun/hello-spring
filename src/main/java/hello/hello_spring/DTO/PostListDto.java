package hello.hello_spring.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListDto {
  @NotNull(message = "id cannot be null")
  private Long PostId;

  @NotNull(message = "title cannot be null")
  private String title;

  @NotNull(message = "content cannot be null")
  private String content;

  @NotNull(message = "memberId cannot be null")
  private Long memberId;
}
