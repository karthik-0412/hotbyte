package com.example.controller;

import com.example.dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet(){}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao dao = new UserDao();
        if (dao.validateUser(email, password)) {
            response.sendRedirect("welcome.jsp");
//        	System.out.print("Welcome");
        } else {
            response.sendRedirect("login.jsp");
//        	System.out.print("Not Welcome");
        }
    }
}