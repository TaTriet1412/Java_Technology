package com.example.Lab7_6.Service;

import com.example.Lab7_6.Entity.Student;

import java.util.List;

public interface StudentService {
    public  Iterable<Student> getAllStudents();
    public Student getStudent(Long id) throws Exception;
    public void deleteStudent(Long id);
    public Student save(Student student);
    public List<Student> findByAgeGreaterThanEqual(Integer age);
    public Integer countStudentsByIeltsScore(Double ieltsScore);
    List<Student> findByNameContaining(String keyword);
    List<Student> descendingByAge();
    List<Student> search456PositionStudents();
}
