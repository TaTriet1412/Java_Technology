package com.demoApp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/images/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("file");

        if (fileName == null || fileName.isEmpty()) {
            resp.getWriter().println("File not found: " + fileName);
            return;
        }
        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/files/" + fileName);
        if (in == null) {
            resp.getWriter().println("File not found: " + fileName);
            return;
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            resp.getOutputStream().write(buffer, 0, length);
        }
        in.close();
    }
}
