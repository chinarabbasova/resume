package com.company.resume.controller;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.resume.util.ControllerUtil;
import dao.inter.UserDaoInter;
import entity.User;
import main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    //    private static BCrypt.Hasher  crypt = BCrypt.withDefaults();
    private static BCrypt.Verifyer verifyer = BCrypt.verifyer();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
//            password = verifyer.hashToString(4,password.toCharArray());
            User user = userDao.findByEmail(email);
            if (user == null) {
                throw new IllegalArgumentException("user doesn`t exist !");
            }
            BCrypt.Result rs = verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
            if (!rs.verified) {
                throw new IllegalArgumentException(" password is incorrect!");
            }
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("users");
        } catch (Exception ex) {
            ControllerUtil.errorPage(response, ex);
        }
    }
}

