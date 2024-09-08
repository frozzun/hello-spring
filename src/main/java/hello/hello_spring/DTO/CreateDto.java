package hello.hello_spring.DTO;

import jakarta.validation.constraints.NotNull;

public class CreateDto {
  @NotNull(message = "Name cannot be null")
  private String name;

  @NotNull(message = "Post cannot be null")
  private String post;

  public String getPost() {
    return post;
  }

  public void setPost(String post) {
    this.post = post;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
