package hello.hello_spring.service;

import hello.hello_spring.domain.Post;
import hello.hello_spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  private final PostRepository postRepository;

  /**
   * 생성자
   */
  @Autowired
  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  /**
   * Find Post
   */
  public Optional<Post> findPost(Long id) {
    return postRepository.findById(id);
  }



  /**
   * Create(생성)
   * Post
   */
  public void create(Post post) {
    postRepository.save(post);
  }

  /**
   * Read(조회)
   * Get
   */
  public List<Post> read() {
    return postRepository.findAll();
  }

  /**
   * Update(수정)
   * Patch
   */
  public String update(Post post) {
    postRepository.save(post);
    return post.getPost();
  }

  /**
   * Delete(삭제)
   * Delete
   */
  public void delete(Post post) {
    postRepository.delete(post);
  }


}
