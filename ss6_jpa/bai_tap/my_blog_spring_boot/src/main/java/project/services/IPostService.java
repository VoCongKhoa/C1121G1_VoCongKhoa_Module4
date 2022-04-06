package project.services;

import project.models.Post;

import java.util.List;

public interface IPostService{
    void save(Post post);

    Post findById(Integer id);

    void update(Post post);

    void remove(Integer id);

    List<Post> searchByContent(String contentSearch);

    List<Post> findAll();

}
