package com.example.Lab7_5.Service;

import com.example.Lab7_5.Entity.Student;
import com.example.Lab7_5.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceIlm implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAllStudents(){
        return  studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) throws Exception{
        return studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found"));
    }

    @Override
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    @Override
    public Student save(Student student){
        return studentRepository.save(student);
    }

    public List<Student> findByAgeGreaterThanEqual(Integer age){
        return studentRepository.searchByAge(age);
    }

    public Integer countStudentsByIeltsScore(Double ieltsScore){
        return studentRepository.searchByIeltsScore(ieltsScore);
    }
    public List<Student> findByNameContaining(String keyword){
        return studentRepository.searchByName(keyword);
    }
}
