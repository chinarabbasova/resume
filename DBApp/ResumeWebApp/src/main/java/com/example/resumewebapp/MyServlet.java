package com.example.resumewebapp;

import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MyServlet", urlPatterns = {"/MyFavoritePage"})
public class MyServlet extends HttpServlet {
    private UserDaoInter userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");

            List<User> users  =userDao.getAllUser();
        try (PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JSP - Hello World</title>");
            out.println("</head>");
            out.println("<body>");
            for(User u : users){
                out.println(u+ "</br>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
