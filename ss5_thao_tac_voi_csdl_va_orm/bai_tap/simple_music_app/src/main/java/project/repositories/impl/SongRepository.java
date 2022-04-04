package project.repositories.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import project.models.Song;
import project.repositories.ISongRepository;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SongRepository implements ISongRepository {

    @Override
    public void save(Song song) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        transaction.begin();
        BaseRepository.entityManager.persist(song);
        transaction.commit();
    }

    @Override
    public List<Song> findAll() {
        TypedQuery<Song> typedQuery = BaseRepository.entityManager.createQuery(
                "select s " +
                        "from song s ", Song.class);
        return typedQuery.getResultList();
//        return BaseRepository.entityManager.createQuery(
//                "select s From song as s", Song.class).getResultList();
    }

    @Override
    public Song findById(Integer id) {
        Song song = BaseRepository.entityManager.find(Song.class, id);
        return song;
//         TypedQuery<Song> typedQuery =  BaseRepository.entityManager.createQuery(
//                "select s " +
//                        "from song s " +
//                        "where s.id = ?1", Song.class);
//         typedQuery.setParameter(1, id);

//         return typedQuery.getSingleResult();
    }

    @Override
    public void update(Song song) {
        Transaction transaction = null;
        try (Session session = BaseRepository.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Song songGetOne = findById(song.getId());
            songGetOne.setName(song.getName());
            songGetOne.setSinger(song.getSinger());
            songGetOne.setCategory(song.getCategory());
            songGetOne.setSource(song.getSource());
            session.saveOrUpdate(songGetOne);
            transaction.commit();
//            return songGetOne;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
//        return null;
    }

    @Override
    public void delete(int idDelete) {
        BaseRepository.entityManager.getTransaction().begin();
        BaseRepository.entityManager.remove(findById(idDelete));
        BaseRepository.entityManager.getTransaction().commit();
    }

    @Override
    public List<Song> searchByName(String nameSearch) {
        TypedQuery<Song> typedQuery = BaseRepository.entityManager.createQuery(
                "select s " +
                        "from song s " +
                        "where s.name like concat('%',?1,'%') ", Song.class);
        typedQuery.setParameter(1, nameSearch);
        return typedQuery.getResultList();
    }
}
