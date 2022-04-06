package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Post;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByPostContentContaining(String contentSearch);

}