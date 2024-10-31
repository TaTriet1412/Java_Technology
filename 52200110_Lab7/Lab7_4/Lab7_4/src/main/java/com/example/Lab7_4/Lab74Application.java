package com.example.Lab7_4;

import com.example.Lab7_4.Entity.Student;
import com.example.Lab7_4.Service.StudentServiceIlm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Lab74Application implements CommandLineRunner {

	@Autowired
	StudentServiceIlm studentService = new StudentServiceIlm();

	public static void main(String[] args) {
		SpringApplication.run(Lab74Application.class, args);
	}

	public void showStudents(){
		List<Student> studentList = (List<Student>) this.studentService.getAllStudents();
		for(Student student:studentList){
			System.out.println(student);
		}
	}

	public void showStudents(List<Student> studentList){
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
		List<Student> studentList  = studentService.findByAgeGreaterThanEqual(19);
		System.out.println("Student with age greater than or equal to 19:");
		showStudents(studentList);
		System.out.println("The number of students with Ielts score of 7.0" + studentService.countStudentsByIeltsScore(7.0));
		studentList = studentService.findByNameContaining("om");
		System.out.println("The students are found:");
		showStudents(studentList);

	}

}

