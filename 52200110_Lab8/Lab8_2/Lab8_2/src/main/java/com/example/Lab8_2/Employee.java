package com.example.Lab8_2;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "employee") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column private Long id;
    @Column private String name;
    @Column private String email;
    @Column private String address;
    @Column private String phone;


}
