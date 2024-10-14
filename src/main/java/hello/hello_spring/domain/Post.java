package hello.hello_spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
//  @JsonBackReference // 자식 방향 참조
  private Member member;


  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
//  @Column(unique = true, length=200) //유일하고 최대 길이가 200.
  private String post;


  public Post(Member member, String name, String post) {
    this.member = member;
    this.name = name;
    this.post = post;
  }
}
