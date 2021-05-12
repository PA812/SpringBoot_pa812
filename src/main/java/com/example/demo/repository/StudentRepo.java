package com.example.demo.repository;


import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    public Student findByPhone(String phone);
    public List<Student> findAllByTeachers(Teacher teacher);
    public  Student getStudentById(Long id);
}
