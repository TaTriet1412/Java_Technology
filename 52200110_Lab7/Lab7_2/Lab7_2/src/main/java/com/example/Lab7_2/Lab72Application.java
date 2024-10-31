package com.example.Lab7_2;

import com.example.Lab7_2.Entity.Student;
import com.example.Lab7_2.Repository.StudentRepository;
import com.example.Lab7_2.Service.StudentService;
import com.example.Lab7_2.Service.StudentServiceIlm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Lab72Application implements CommandLineRunner {

	@Autowired
	StudentServiceIlm studentService = new StudentServiceIlm();

	public static void main(String[] args) {
		SpringApplication.run(Lab72Application.class, args);
	}

	public void showStudents(){
		List<Student> studentList = (List<Student>) this.studentService.getAllStudents();
		for(Student student:studentList){
			System.out.println(student);
		}
	}



	@Override
	public void run(String... args) throws Exception {

		Student student1 = new Student(1L, "Tom", 20, "tom12@gmail.com", 9.0);
		Student student2 = new Student(2L, "John", 21, "jj12@gmail.com", 8.5);
		Student student3 = new Student(3L, "Lysa", 22, "lyly@gmail.com", 8.5);
		studentService.save(student1);
		studentService.save(student2);
		studentService.save(student3);
		showStudents();
		student1.setIelts(8.0);
		System.out.println("After updating students");
		showStudents();
		this.studentService.deleteStudent(1L);
		System.out.println("After deleting students");
		showStudents();
	}
}
