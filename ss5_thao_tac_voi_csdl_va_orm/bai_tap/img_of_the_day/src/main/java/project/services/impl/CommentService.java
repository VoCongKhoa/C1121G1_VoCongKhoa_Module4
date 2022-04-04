package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Comment;
import project.repositories.ICommentRepository;
import project.services.ICommentService;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository iCommentRepository;

    @Override
    public List<Comment> findAll() {
        return this.iCommentRepository.findAll();
    }

    @Override
    public void like(Comment comment) {
        iCommentRepository.like(comment);
    }

    @Override
    public void save(Comment comment) {
        this.iCommentRepository.save(comment);
    }

    @Override
    public Comment findById(Integer id) {
        return iCommentRepository.findById(id);
    }

}
