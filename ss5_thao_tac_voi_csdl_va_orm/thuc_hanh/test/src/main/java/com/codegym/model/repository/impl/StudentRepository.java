package com.codegym.model.repository.impl;

import com.codegym.model.bean.Student;
import com.codegym.model.repository.IStudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {

    @Override
    public void save(Student student) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        transaction.begin();
        BaseRepository.entityManager.persist(student);
        transaction.commit();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery = BaseRepository.entityManager.createQuery(
                "select s " +
                        "from student as s" , Student.class);
//        typedQuery.setParameter(1, id);

        return typedQuery.getResultList();
//        return BaseRepository.entityManager.createQuery(
//                "SELECT s FROM student as s", Student.class).getResultList();
    }

    @Override
    public Student findById(Integer id) {
//         TypedQuery<Student> typedQuery =  BaseRepository.entityManager.createQuery(
//                "select s " +
//                        "from student s " +
//                        "where s.id = ?1", Student.class);
//         typedQuery.setParameter(1, id);

//         return typedQuery.getSingleResult();
        return BaseRepository.entityManager.find(Student.class, id);
    }
}
