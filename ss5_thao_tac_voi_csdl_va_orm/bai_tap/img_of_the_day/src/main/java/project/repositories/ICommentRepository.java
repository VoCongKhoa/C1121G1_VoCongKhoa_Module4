package project.repositories;

import project.models.Comment;

import java.util.List;

public interface ICommentRepository {
    List<Comment> findAll();

    void like(Comment comment);
        void save(Comment comment);
    Comment findById(Integer id);
}
