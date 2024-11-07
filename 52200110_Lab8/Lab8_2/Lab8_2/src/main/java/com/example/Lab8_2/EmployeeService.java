package com.example.Lab8_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setAddress(request.getAddress());
        employee.setPhone(request.getPhone());
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(String id) {
        return employeeRepository.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public Employee updateEmployee(EmployeeRequest request, String id) {
        Employee employee = getEmployee(id);

        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setEmail(request.getEmail());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(Long.valueOf(id));
    }

}
