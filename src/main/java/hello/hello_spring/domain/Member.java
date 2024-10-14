package hello.hello_spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;


  @Builder
  public Member(String email, String password) {
    this.email = email;
    this.password = password;
  }

  @CreationTimestamp
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  private boolean isDeleted;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//  @JsonManagedReference // 부모 방향 참조
  private List<Post> posts;
}
