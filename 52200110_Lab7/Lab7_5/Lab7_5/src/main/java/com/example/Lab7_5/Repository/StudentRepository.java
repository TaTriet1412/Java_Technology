package com.example.Lab7_5.Repository;

import com.example.Lab7_5.Entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
    @Query(
            value = "SELECT * FROM student s WHERE s.age >= :age",
            nativeQuery = true
    )
    public List<Student> searchByAge(@Param("age") Integer age);

    @Query(
            value = "SELECT COUNT(*) FROM student s WHERE s.ielts = :ieltsScore",
            nativeQuery = true
    )
    public Integer searchByIeltsScore(@Param("ieltsScore") Double ieltsScore);

    @Query(
            value = "SELECT * FROM student s WHERE s.name LIKE %:keyword%",
            nativeQuery = true
    )
    List<Student> searchByName(@Param("keyword") String keyword);
}
