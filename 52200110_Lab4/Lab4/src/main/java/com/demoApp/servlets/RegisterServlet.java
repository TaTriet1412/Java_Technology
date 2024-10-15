package com.demoApp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String birthday = req.getParameter("birthday");
        String birthtime = req.getParameter("birthtime");
        String gender = req.getParameter("gender");
        String country = req.getParameter("country");
        String[] favoriteIdes = req.getParameterValues("favorite_ide[]");
        String toeic = req.getParameter("toeic");
        String msg = req.getParameter("message");


        boolean isErr = false;
        StringBuilder errorMessage = new StringBuilder();

        if (isEmpty(name)) {
            errorMessage.append("Name is required<br>");
            isErr = true;
        }
        if (isEmpty(email)) {
            errorMessage.append("Email is required<br>");
            isErr = true;
        }
        if (isEmpty(gender)) {
            errorMessage.append("Gender is required<br>");
            isErr = true;
        }
        if (isEmpty(country) || country.equals("Select a country")) {
            errorMessage.append("Country is required<br>");
            isErr = true;
        }
        if (favoriteIdes == null || favoriteIdes.length == 0) {
            errorMessage.append("Select at least one IDE<br>");
            isErr = true;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("table { border-collapse: collapse; width: 500px; margin: 20px auto; }");
        out.println(".error { color: red; margin-bottom: 20px; }");
        out.println("table, th, td { border: 1px solid black; }");
        out.println("td { padding: 12px; }");
        out.println("td:first-child { width: 180px; color: blue; }");
        out.println("td:last-child { color: green; }");
        out.println(".error-message { color: red; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        if (isErr) {
            out.println("<div class='error'>");
            out.println("<h2>Registration Error</h2>");
            out.println(errorMessage.toString());
            out.println("<a href='javascript:history.back()'>Go Back</a>");
            out.println("</div>");
        } else {
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Full Name</td>");
            out.println("<td>" + formatHTML(name) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Email</td>");
            out.println("<td>" + formatHTML(email) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Birthday</td>");
            out.println("<td>" + formatHTML(birthday) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Hour of birthday</td>");
            out.println("<td>" + formatHTML(birthtime) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Gender</td>");
            out.println("<td>" + formatHTML(gender) + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Nationality</td>");
            out.println("<td>" + formatHTML(country) + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>IDE Favourite</td>");
            out.println("<td>");
            for (String ide : favoriteIdes) {
                out.println( formatHTML(ide) + "<br>");
            }
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>TOEIC Score</td>");
            out.println("<td>" + formatHTML(toeic) + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Introduction of yourself</td>");
            out.println("<td>" + formatHTML(msg) + "</td>");
            out.println("</tr>");

            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    private boolean isEmpty(String value) {
        return (value == null || value.trim().isEmpty());
    }

    private String formatHTML(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req,resp);
    }
}