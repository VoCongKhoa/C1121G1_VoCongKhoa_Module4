package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.models.Post;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select post.post_id, post.post_content, post.post_date_modified, post.post_intro, post.post_thumbnail_source, post.post_title, post.author_id from post where post.post_title like concat('%',:contentSearch,'%')", nativeQuery = true)
//    Page<Post> findAllByPostContentSearch(@Param("contentSearch") String contentSearch, Pageable pageable);
    List<Post> findAllByPostTitleSearch(@Param("contentSearch") String contentSearch);

    @Query(value = "select post.* from post " +
            "inner join post_category on post.post_id = post_category.post_id " +
            "inner join category on category.category_id = post_category.category_id " +
            "where category.category_id = :categoryId", nativeQuery = true)
    List<Post> findAllByCategory(@Param("categoryId") int categoryId);

//    Page<Post> findAllByPostTitleContaining(Pageable pageable, String valueSearch);
//    Page<Post> findAllByOrderByPostDateModified(Pageable pageable);
//    Page<Post> findByOrderByPostDateModified(Pageable pageable);
}