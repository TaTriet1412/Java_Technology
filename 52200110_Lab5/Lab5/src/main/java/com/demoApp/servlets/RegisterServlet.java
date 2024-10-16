package com.demoApp.servlets;

import com.demoApp.DAO.AccountDAO;
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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currPassword = req.getParameter("password");
        String currName = req.getParameter("name");
        String currEmail = req.getParameter("email");
        String currPasswordConfirm = req.getParameter("password-confirm");

        HttpSession sessionHttp = req.getSession();

        if(currName.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter your name");
            resp.sendRedirect( "/Lab5/register");
            return;
        }
        if(currEmail.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter your email");
            resp.sendRedirect( "/Lab5/register");
            return;
        }
        if(currPassword.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter your password");
            resp.sendRedirect( "/Lab5/register");
            return;
        }if(currPasswordConfirm.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter your password confirm");
            resp.sendRedirect( "/Lab5/register");
            return;
        }
        if(!currPassword.equals(currPasswordConfirm)){
            sessionHttp.setAttribute("sessionErrMsg", "Password confirm is not matched");
            resp.sendRedirect( "/Lab5/register");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();

        if(accountDAO.getAll().isEmpty()){
            Account newAc = new Account(currName,currEmail,currName,currPassword);
            accountDAO.add(newAc);
            resp.sendRedirect( "/Lab5/login");
            return;
        }
        for(Account ac:accountDAO.getAll()){
            if(ac.getUsername().equals(currName)){
                sessionHttp.setAttribute("sessionErrMsg", "Username is existed");
                resp.sendRedirect( "/Lab5/register");
            }else{
                Account newAc = new Account(currName,currEmail,currName,currPassword);
                accountDAO.add(newAc);
                resp.sendRedirect( "/Lab5/login");
            }
            return;

        }


    }
}
