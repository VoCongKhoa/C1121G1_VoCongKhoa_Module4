package com.codegym.model.service.impl;

import com.codegym.model.bean.Student;
import com.codegym.model.repository.IStudentRepository;
import com.codegym.model.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id);
    }
}
