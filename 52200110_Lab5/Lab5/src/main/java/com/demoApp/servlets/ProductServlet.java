package com.demoApp.servlets;

import com.demoApp.DAO.ProductDAO;
import com.demoApp.DTO.Account;
import com.demoApp.DTO.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/product.jsp").forward(req,resp);
        HttpSession session = req.getSession(true);
        session.setAttribute("productList", productDAO.getAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currName = req.getParameter("name");
        String currPriceStr = req.getParameter("price");
        HttpSession sessionHttp = req.getSession();
        int currPrice;

        if(currName.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter product's name");
            resp.sendRedirect( "/Lab5/product");
            return;
        }
        if(currPriceStr.trim().equals("")){
            sessionHttp.setAttribute("sessionErrMsg", "Please enter product's price");
            resp.sendRedirect( "/Lab5/product");
            return;
        }
        try {
            currPrice = Integer.parseInt(currPriceStr);
            if(currPrice < 0 ){
                sessionHttp.setAttribute("sessionErrMsg", "Please enter positive integer for product's price");
                resp.sendRedirect( "/Lab5/product");
                return;
            }
        } catch (NumberFormatException e) {
            sessionHttp.setAttribute("sessionErrMsg", "Please enter positive integer for product's price");
            resp.sendRedirect( "/Lab5/product");
            return;
        }
        for(Product product: productDAO.getAll()){
            if(product.getName().equalsIgnoreCase(currName)){
                sessionHttp.setAttribute("sessionErrMsg", "This product's name is existed");
                resp.sendRedirect( "/Lab5/product");
                return;
            }
        }

        Product newPr = new Product(currPrice,currName);
        productDAO.add(newPr);
        resp.sendRedirect( "/Lab5/product");
        return;



    }
}
