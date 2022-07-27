package com.example.resumewebapp;

import dao.impl.SkillDaoImpl;
import dao.impl.UserDaoImpl;
import dao.inter.SkillDaoInter;
import dao.inter.UserDaoInter;
import entity.Skill;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;

@WebServlet(name = "MyServlet", urlPatterns = {"/MyFavoritePage"})
public class MyServlet extends HttpServlet {
    private UserDaoInter userDao = new UserDaoImpl();
    private SkillDaoInter skillDao = new SkillDaoImpl();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        Skill s = skillDao.getById(id);
        s.setName(name);
        skillDao.updateSkill(s);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int id = Integer.valueOf( request.getParameter("id"));
        String name = String.valueOf(request.getParameter("name"));
        Skill s = new Skill(id,name);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JSP - Hello World</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println(userDao.getAllUser()   + "<br>");
            out.println(skillDao.getAllSkills().get(0).getId() + "<br>");
            out.println(skillDao.getAllSkills() + "<br>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        String name = String.valueOf(request.getParameter("name"));
//        String surname = String.valueOf(request.getParameter("surname"));
//        String phone = String.valueOf(request.getParameter("phone"));
//        String email = String.valueOf(request.getParameter("email"));
//        String profile = String.valueOf(request.getParameter("profile"));
//
//        User u = new User(0, name, surname, phone, email, profile, null, null, null, null);
//        System.out.println(u);
//        boolean users = userDao.addUser(u);
//        // int id = Integer.parseInt(request.getParameter("id"));
//        boolean userAdd = userDao.addUser(u);
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>JSP - Hello World</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println(u + "<br>");
//            //out.println(userDao.getAllUser()   + "<br>");
//            out.println(userDao.getAllUser().get(0).getId() + "<br>");
//            out.println("</body>");
//            out.println("</html>");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = getAllDataFromRequest(request);
        String name = String.valueOf(request.getAttribute("name"));
        String surname = String.valueOf(request.getAttribute("surname"));
        String phone = String.valueOf(request.getParameter("phone"));
        String email = String.valueOf(request.getParameter("email"));
        String profile = String.valueOf(request.getParameter("profile"));

        User u = new User(0, name, surname, phone, email, profile, null, null, null, null);
        System.out.println(u);
        boolean users = userDao.addUser(u);
        response.setContentType("text/html");

        List<User> daoAllUser = userDao.getAllUser();
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>It`s Posting Beach </title>");
            out.println("</head>");
            out.println("<body>");
            for (User u1 : daoAllUser) {
                out.println(u + "<br>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAllDataFromRequest(HttpServletRequest request) throws IOException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return body;
    }
}
