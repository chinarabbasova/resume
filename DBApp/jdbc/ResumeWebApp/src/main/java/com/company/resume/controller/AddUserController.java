package com.company.resume.controller;

import dao.inter.UserDaoInter;
import entity.User;
import main.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddUserController", urlPatterns = {"/addUser"})
public class AddUserController extends HttpServlet {
    private UserDaoInter userDao = Context.instanceUserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getRequestDispatcher("adduser.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
             String idStr = request.getParameter("id");
             Integer id = 0;
             if(idStr!=null&& !(idStr.trim().isEmpty()))
                 id = Integer.parseInt(idStr);
       
            String nameA = request.getParameter("name");
            String surnameA = request.getParameter("surname");
            String phoneA = request.getParameter("phone");
            String emailA = request.getParameter("email");
            String passwordA = request.getParameter("password");
            String profileDesc = request.getParameter("profileDescription");
            String addressA = request.getParameter("address");

            User u = new User();
            u.setId(id);
            u.setName(nameA);
            u.setSurname(surnameA);
            u.setPhone(phoneA);
            u.setEmail(emailA);
            u.setPassword(passwordA);
            u.setProfileDescription(profileDesc);
            u.setAddress(addressA);
            userDao.addUser(u);
        }
      response.sendRedirect("users");
    }


//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
//            ServletException, IOException {
//        try {
//            String userIdStr = request.getParameter("id");
//            if (userIdStr == null || userIdStr.trim().isEmpty()) {
//                // request.setAttribute("msg", " specify id");
//                throw new IllegalArgumentException("id is not specified");
//            }
//            Integer userId = Integer.parseInt(userIdStr);
//            UserDaoInter userDao = Context.instanceUserDao();
//            User u = userDao.getById(userId);
//            if (u == null) {
//                throw new IllegalArgumentException("  There is no user with this id");
//            }
//            request.setAttribute("owner", true);
//            request.setAttribute("user", u);
//            request.getRequestDispatcher("userdetails.jsp").forward(request, response);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            response.sendRedirect("error?msg=" + ex.getMessage());
//        }
//    }

}

