package project.services;

import project.models.Post;

import java.util.List;

public interface IPostService{
    List<Post> findAll();

    void save(Post post);

    Post findById(Long id);

    void update(Post post);

    void remove(Long id);

    List<Post> searchByName(String nameSearch);

    boolean insertWithStoredProcedure(Post post);
}
