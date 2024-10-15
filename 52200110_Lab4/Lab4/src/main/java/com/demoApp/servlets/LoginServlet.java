package com.demoApp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private HashMap<String,String> userList;



    @Override
    public void init() throws ServletException {
        System.out.println("Starting Login Servlet!");
        System.out.println("Initializing user list....!");
        userList = new HashMap<>();
        userList.put("tatriet","123");
        userList.put("dangtrong","abc");
        userList.put("mailan","234");
        userList.put("hongphuc","def");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currUsername = req.getParameter("username");
        String currPassword = req.getParameter("password");
        resp.setContentType("text/html");
        PrintWriter out  = resp.getWriter();

        if(!userList.isEmpty()){
            userList.forEach((user,pass) -> {
                if(user.equals(currUsername) && pass.equals(currPassword)){
                    out.println("Name/Password match");
                    out.close();
                    return;
                }
            });
        }
        out.println("Name/Password doesn't match");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    public void destroy() {
        System.out.println("Delete Login Servlet!");
    }
}
