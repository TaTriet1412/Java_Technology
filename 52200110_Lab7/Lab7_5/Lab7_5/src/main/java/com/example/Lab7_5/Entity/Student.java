package com.example.Lab7_5.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Entity
public class Student {
    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String email;

    @Column
    private Double ielts;

    public Student(){}
    public Student(Long id, String name, Integer age, String email, Double ielts){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.ielts = ielts;
    }

}
