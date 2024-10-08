package org.example.DTO;

import javax.persistence.*;
import java.util.LinkedList;

import lombok.Data;

@Data
@Entity
@Table(name = "manufacture")
public class Manufacture {
//    private LinkedList<Phone> listPhone;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private int employee;

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Manufacture[id = " + getId() + ", name = " + getName() + ", location = " +getLocation() + ", empoyee = " + getEmployee() + "]";
    }
}
