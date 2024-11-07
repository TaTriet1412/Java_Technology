package com.example.Lab8_1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "About this site";
    }

    @PostMapping("/contact")
    public String submitContactForm(@RequestParam("name") String name, @RequestParam("age") Integer age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "contact-result"; // This refers to contact-result.html in the resources/templates directory
    }


}
