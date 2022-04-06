package project.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.models.Post;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByPostContentContaining(String contentSearch);

    @Query(value = "select post.* from post \n" +
            "inner join post_category on post.post_id = post_category.post_id\n" +
            "inner join category on category.category_id = post_category.category_id\n" +
            "where category.category_id = :categoryId", nativeQuery = true)
    List<Post> findAllByCategory(@Param("categoryId") int categoryId);

    Page<Post> findAllByPostTitleContaining(Pageable pageable, String valueSearch);
    Page<Post> findAllByOrderByPostDateModified(Pageable pageable);
//    Page<Post> findByOrderByPostDateModified(Pageable pageable);
}