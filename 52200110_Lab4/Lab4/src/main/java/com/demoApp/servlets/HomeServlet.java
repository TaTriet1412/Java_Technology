package com.demoApp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home/*")
public class HomeServlet extends HttpServlet {

    public HomeServlet() {

    }

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Home Servlet!!!");
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page = req.getParameter("page");

        if (page == null || page.trim().isEmpty()) {
            resp.setContentType("text/html");
            resp.getWriter().println("Welcome to my website. This is the homepage");
            return;
        }

        String jspPageTag;
        switch (page.toLowerCase()) {
            case "about":
                jspPageTag = "/WEB-INF/jsp/about.jsp";
                break;
            case "contact":
                jspPageTag = "/WEB-INF/jsp/contact.jsp";
                break;
            case "help":
                jspPageTag = "/WEB-INF/jsp/help.jsp";
                break;
            default:
                resp.setContentType("text/html");
                resp.getWriter().println("Welcome to my website. This is the homepage");
                return;
        }

        req.getRequestDispatcher(jspPageTag).forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
