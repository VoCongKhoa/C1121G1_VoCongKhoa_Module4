package project.repositories;

import project.models.Post;

import java.util.List;

public interface IPostRepository {
    List<Post> findAll();

    Post findById(Long id);

    void save(Post post);

    void remove(Long id);

    void update(Post post);

    List<Post> searchByName(String nameSearch);

    boolean insertWithStoredProcedure(Post post);
}