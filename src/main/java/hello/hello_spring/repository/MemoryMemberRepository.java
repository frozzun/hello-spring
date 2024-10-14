//package hello.hello_spring.repository;
//
//import hello.hello_spring.domain.Post;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class MemoryMemberRepository implements PostRepository{
//
//  private static Map<Long, Post> store = new HashMap<>();
//  private static long sequence = 0L;
//
//  @Override
//  public Post save(Post member) {
//    member.setId(++sequence);
//    store.put(member.getId(), member);
//    return member;
//  }
//  @Override
//  public Optional<Post> findById(Long id) {
//    return Optional.ofNullable(store.get(id));
//  }
//  @Override
//  public List<Post> findAll() {
//    return new ArrayList<>(store.values());
//  }
//  @Override
//  public Optional<Post> findByName(String name) {
//    return store.values().stream()
//        .filter(member -> member.getName().equals(name))
//        .findAny();
//  }
//  public void clearStore() {
//    store.clear();
//  }
//}
