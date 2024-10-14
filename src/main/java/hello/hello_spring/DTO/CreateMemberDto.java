package hello.hello_spring.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberDto {

  @NotNull(message = "Email cannot be null")
  private String email;
  @NotNull(message = "PassWord cannot be null")
  private String password;
}
