package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Post;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostByPostContentContaining(String contentSearch);
}