package com.example.Lab7_2.Service;

import com.example.Lab7_2.Entity.Student;
import org.springframework.stereotype.Service;

public interface StudentService {
    public  Iterable<Student> getAllStudents();
    public Student getStudent(Long id) throws Exception;
    public void deleteStudent(Long id);
    public Student save(Student student);
}
