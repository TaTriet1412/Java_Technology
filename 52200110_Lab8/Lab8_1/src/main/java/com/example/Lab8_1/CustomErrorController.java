package com.example.Lab8_1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);
        if (statusCode == 404) {
            model.addAttribute("errorMessage", "Custom 404 - Page Not Found");
        } else if (statusCode == 405) {
            model.addAttribute("errorMessage", "Custom 405 - Method Not Allowed");
        } else {
            model.addAttribute("errorMessage", "Custom Error - Something went wrong");
        }
        return "errorPage";
    }

}
