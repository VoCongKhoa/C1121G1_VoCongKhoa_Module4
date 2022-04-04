package project.repositories.impl;

import org.springframework.stereotype.Repository;
import project.models.Comment;
import project.repositories.ICommentRepository;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepository implements ICommentRepository {

    @Override
    public void save(Comment comment) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        transaction.begin();
        BaseRepository.entityManager.persist(comment);
        transaction.commit();
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> typedQuery = BaseRepository.entityManager.createQuery(
                "select s " +
                        "from comment s ", Comment.class);
        return typedQuery.getResultList();
    }

    @Override
    public void like(Comment comment) {
        EntityTransaction entityTransaction = BaseRepository.entityManager.getTransaction();
        entityTransaction.begin();
        comment.setCommentLike(comment.getCommentLike()+1);
        BaseRepository.entityManager.merge(comment);
        entityTransaction.commit();
    }

    @Override
    public Comment findById(Integer id) {
        return BaseRepository.entityManager.find(Comment.class, id);
    }
}
