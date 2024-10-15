package com.demoApp.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/images/image2")
public class ImageServlet2 extends HttpServlet {
    private final int ARBITRARY_SIZE = 1048;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition","attachment; filename");
        try (InputStream in = req.getServletContext().getResourceAsStream("/WEB-INF/images/cat.jpg");
            OutputStream out = resp.getOutputStream();
        ){
            byte[] bytes = new byte[1048];
            int numByte;
            while ((numByte =in.read(bytes))>0){
                out.write(bytes,0,numByte);
            }
        }
    }
}
