package com.codegym.model.repository;

import com.codegym.model.bean.Student;

import java.util.List;

public interface IStudentRepository {
    void save(Student student);
    List<Student> findAll();
    Student findById(Integer id);
}
