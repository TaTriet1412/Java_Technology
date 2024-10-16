package com.demoApp.servlets;

import com.demoApp.DAO.AccountDAO;
import com.demoApp.DAO.ProductDAO;
import com.demoApp.DTO.Account;
import com.demoApp.utils.HibernateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currUsername = req.getParameter("username");
        String currPassword = req.getParameter("password");
        HttpSession sessionHttp = req.getSession();

        if(currUsername.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter username");
            resp.sendRedirect( "/Lab5/login");
            return;
        }
        if(currPassword.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter password");
            resp.sendRedirect( "/Lab5/login");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();
        Account currAcc = accountDAO.get(currUsername,currPassword);
        if(currAcc!=null){
            resp.sendRedirect( "/Lab5/product");
            sessionHttp.setAttribute("sessionAccount", "is access");
            sessionHttp.setAttribute("userName", currUsername);
            return;
        }else{
            sessionHttp.setAttribute("sessionErrMsg", "Username or password is wrong");
            resp.sendRedirect( "/Lab5/login");
        }
        return;
    }
}
