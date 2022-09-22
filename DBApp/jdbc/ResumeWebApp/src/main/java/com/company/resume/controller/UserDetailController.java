package com.company.resume.controller;

import dao.inter.UserDaoInter;
import entity.User;
import main.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetails"})
public class UserDetailController extends HttpServlet {
    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Integer id = null;
        if (idStr != null && !(idStr.trim().isEmpty())) {
            id = Integer.parseInt(idStr);
//        String nationalityIdStr = request.getParameter("nId");
//        Integer nationalityId = null;
//        if (nationalityIdStr != null && !(nationalityIdStr.trim().isEmpty())) {
//            nationalityId = Integer.parseInt(nationalityIdStr);
        }
        String action = request.getParameter("action");
        if (action.equals("update")) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String profileDescription = request.getParameter("profile_description");

            System.out.println("name " + name);
            System.out.println("surname " + surname);
            System.out.println("address " + address);

            User user = userDao.getById(id);
            user.setName(name);
            user.setSurname(surname);
            user.setAddress(address);
            user.setEmail(email);
            user.setPhone(phone);
            user.setProfileDescription(profileDescription);
            userDao.updateUser(user);
        } else if (action.equals("delete")) {
            userDao.removeUser(id);
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                // request.setAttribute("msg", " specify id");
                throw new IllegalArgumentException("id is not specified");
            }
            Integer userId = Integer.parseInt(userIdStr);
            UserDaoInter userDao = Context.instanceUserDao();
            User u = userDao.getById(userId);
            if (u == null) {
                throw new IllegalArgumentException("  There is no user with this id");
            }
            request.setAttribute("owner", true);
            request.setAttribute("user", u);
            request.getRequestDispatcher("userdetails.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }

}

