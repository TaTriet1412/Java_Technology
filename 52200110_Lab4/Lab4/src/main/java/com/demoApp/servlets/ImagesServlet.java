package com.demoApp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/images")
public class ImagesServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Starting Images Servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/images.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
        System.out.println("Delete Images Servlet");
    }
}
