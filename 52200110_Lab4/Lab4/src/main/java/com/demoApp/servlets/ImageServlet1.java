package com.demoApp.servlets;


import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/images/image1")
public class ImageServlet1 extends HttpServlet {
    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        ServletOutputStream outputStream = resp.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(getServletContext().getRealPath("/WEB-INF/images/dog.jpg"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        int ch = 0;
        while((ch = bufferedInputStream.read()) != -1){
            bufferedOutputStream.write(ch);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
        outputStream.close();
        fileInputStream.close();

    }
}
