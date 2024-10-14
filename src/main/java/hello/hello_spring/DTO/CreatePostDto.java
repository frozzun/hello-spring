package hello.hello_spring.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDto {
  @NotNull(message = "member_id cannot be null")
  private Long member_id;

  @NotNull(message = "Name cannot be null")
  private String name;

  @NotNull(message = "Post cannot be null")
  private String post;

}
