package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.Post;

import java.util.List;

public interface IPostService{
    Page<Post> findAllWithSearch(Pageable pageable, String valueSearch);
    Page<Post> findAllWithSort(Pageable pageable);

    List<Post> findAllByCategory(int categoryId);

    void save(Post post);

    Post findById(Integer id);

    void update(Post post);

    void remove(Integer id);

    List<Post> searchByContent(String contentSearch);

    Page<Post> findAll(Pageable pageable);

//    List<Post> findAllFruits(int i);
}
