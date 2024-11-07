package com.example.Lab8_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("employees", employeeService.getEmployees());
        return "index";
    }

    @GetMapping("/employees")
    public String indexEmployee(Model model){
        model.addAttribute("employees", employeeService.getEmployees());
        return "index";
    }

    @GetMapping("/employees/add")
    public String add(){
        return "add";
    }

    @PostMapping("/employees/add")
    public String createEmployee(@RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("address") String address,@RequestParam("phone") String phone){
        Employee savedEmployee = employeeService.createEmployee(new EmployeeRequest(name,email,address,phone));
        return "redirect:/";
    }

    @GetMapping("/employees/edit/{id}")
    public String updateEmployeeView(Model model,@PathVariable String id){
        model.addAttribute("employee",employeeService.getEmployee(id));
        return "edit";
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("address") String address,@RequestParam("phone") String phone,@PathVariable String id){
        Employee savedEmployee = employeeService.updateEmployee(new EmployeeRequest(name,email,address,phone),id);
        return "redirect:/";
    }

    @PostMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }




}
