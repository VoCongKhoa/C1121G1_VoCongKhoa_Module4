package project.repositories;

import org.springframework.stereotype.Repository;
import project.models.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Transactional
@Repository
public class PostRepository implements IPostRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("select p from post p", Post.class).getResultList();
    }

    @Override
    public void save(Post post) {
        entityManager.persist(post);
    }

    @Override
    public Post findById(Long id) {
        return entityManager.find(Post.class,id);
    }

    @Override
    public void update(Post post) {
        entityManager.merge(post);

    }

    @Override
    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public List<Post> searchByName(String nameSearch) {
//        List<Post> result = new ArrayList<>();
//        for (Post post : findAll()) {
//            if (post.getFirstName().toLowerCase(Locale.ROOT).contains(nameSearch.trim().toLowerCase(Locale.ROOT))){
//                result.add(post);
//            }
//        }
//        return result;
        return null;
    }

    @Override
    public boolean insertWithStoredProcedure(Post post) {
//        String sql = "CALL Insert_Customer(:firstName, :lastName)";
//        Query query = entityManager.createNativeQuery(sql);
//        query.setParameter("firstName", post.getFirstName());
//        query.setParameter("lastName", post.getLastName());
//        return query.executeUpdate() == 0;
        return false;
    }
}
