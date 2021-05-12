package com.example.demo.service;


import com.example.demo.model.Role;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService implements UserDetailsService {

    private StudentRepo studentRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }





    public Student save(Student student) {

        student.setRoles(Collections.singleton(new Role(1L, "user_role")));

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        return studentRepo.saveAndFlush(student);
    }

    public Student getById(Long id) {
        if (id == null) {
            return null;
        } else {
            return studentRepo.getStudentById(id);
        }

    }

    public List<Student> showAll(){
        return studentRepo.findAll();
    }


    public Student byPhone(String phone){
        return studentRepo.findByPhone(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Student student = studentRepo.findByPhone(phone);
        if (student == null) {
            throw new UsernameNotFoundException("student with this phone not found");
        }
        return student;
    }
}
