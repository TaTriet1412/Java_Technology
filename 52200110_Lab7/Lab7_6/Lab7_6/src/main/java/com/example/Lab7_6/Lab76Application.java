package com.example.Lab7_6;

import com.example.Lab7_6.Entity.Student;
import com.example.Lab7_6.Service.StudentServiceIlm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Lab76Application implements CommandLineRunner {

	@Autowired
	StudentServiceIlm studentService = new StudentServiceIlm();

	public static void main(String[] args) {
		SpringApplication.run(Lab76Application.class, args);
	}

	public void showStudents() {
		List<Student> studentList = (List<Student>) this.studentService.getAllStudents();
		for (Student student : studentList) {
			System.out.println(student);
		}
	}

	public void showStudents(List<Student> studentList) {
		for (Student student : studentList) {
			System.out.println(student);
		}
	}


	@Override
	public void run(String... args) throws Exception {

		Student student1 = new Student(1L, "Tom", 20, "tom12@gmail.com", 9.0);
		Student student2 = new Student(2L, "John", 21, "jj12@gmail.com", 8.5);
		Student student3 = new Student(3L, "Lysa", 20, "lyly@gmail.com", 8.0);
		Student student4 = new Student(4L, "Bob", 21, "bob@gmail.com", 7.5);
		Student student5 = new Student(5L, "Peter", 19, "peter@gmail.com", 6.5);
		Student student6 = new Student(6L, "Kai", 18, "kai@gmail.com", 8.0);
		Student student7 = new Student(7L, "Sunny", 18, "sunny@gmail.com", 7.0);
		Student student8 = new Student(8L, "Yu", 19,"yu@gmail.com", 6.5);
		Student student9 = new Student(9L, "Yin", 21, "yin@gmail.com", 6.0);
		Student student10 = new Student(10L, "Anna", 22, "anna@gmail.com", 8.0);
		studentService.save(student1);
		studentService.save(student2);
		studentService.save(student3);
		studentService.save(student4);
		studentService.save(student5);
		studentService.save(student6);
		studentService.save(student7);
		studentService.save(student8);
		studentService.save(student9);
		studentService.save(student10);
		showStudents();
		List<Student> studentList = studentService.descendingByAge();
		System.out.println("Student is sorted by descending age and ascending ielts:");
		showStudents(studentList);
		studentList = studentService.search456PositionStudents();
		System.out.println("The 4th, 5th, and 6th students :");
		showStudents(studentList);

	}
}