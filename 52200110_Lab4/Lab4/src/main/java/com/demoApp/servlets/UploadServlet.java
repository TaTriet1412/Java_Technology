package com.demoApp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/UploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class UploadServlet extends HttpServlet {
    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(
            Arrays.asList("txt", "doc", "docx", "img", "pdf", "rar", "zip")
    );
    private String uploadPath;

    @Override
    public void init() {
        uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadsDir = new File(uploadPath);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try {
            String fileName = req.getParameter("fileName");
            Part filePart = req.getPart("file");
            boolean isOverride = req.getParameter("override") != null;
            String originFileName = filePart.getSubmittedFileName();
            String fileExt = getFileExt(originFileName);

            if (!ALLOWED_EXTENSIONS.contains(fileExt.toLowerCase())) {
                outMsg(resp, "Unsupported this file extension");
                return;
            }
            String fileWithFullPath = uploadPath + File.separator + fileName + "." + fileExt;
            File file = new File(fileWithFullPath);

            if (  file.exists() && !isOverride) {
                outMsg(resp, "File already exists");
                return;
            }
            filePart.write(fileWithFullPath);
            String link = req.getContextPath() + "/uploads/" + fileName + "." + fileExt;
            String msg = file.exists() && isOverride ?
                    "File has been overridden" : "File has been uploaded";
            outMsgWhenSuccess(resp, msg, link);

        } catch (Exception e) {
            outMsg(resp, "Error process uploading file: " + e.getMessage());
        }
    }

    private String getFileExt(String fileName) {
        int indexOfDot = fileName.lastIndexOf('.');
        if (indexOfDot < 0) {
            return "";
        }
        return fileName.substring(indexOfDot + 1).toLowerCase();
    }

    private void outMsg(HttpServletResponse resp, String msg) throws IOException {
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<h2>" + msg + "</h2>");
        resp.getWriter().println("</body></html>");
    }

    private void outMsgWhenSuccess(HttpServletResponse resp, String msg, String link)
            throws IOException {
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<h2>" + msg + "</h2>");
        resp.getWriter().println("File uploaded. Click  <a href='" + link + "'>here</a> to visit file<br>");
        resp.getWriter().println("</body></html>");
    }
}
