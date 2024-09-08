package hello.hello_spring.domain;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
//  @Column(unique = true, length=200) //유일하고 최대 길이가 200.
  private String post;

  public Member() {}
  public Member(String name, String post) {
    this.name = name;
    this.post = post;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPost() {
    return post;
  }

  public void setPost(String name) {
    this.post = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
