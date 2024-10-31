package com.example.Lab7_3.Service;

import com.example.Lab7_3.Entity.Student;

public interface StudentService {
    public  Iterable<Student> getAllStudents();
    public Student getStudent(Long id) throws Exception;
    public void deleteStudent(Long id);
    public Student save(Student student);
}
