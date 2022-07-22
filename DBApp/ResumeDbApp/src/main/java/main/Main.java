package main;

import bean.User;
import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
//        List<User> list =  userDao.getAllUser();
//        System.out.println("list= " + list);
//        userDao.removeUser(2);
//       List<User> list2 =  userDao.getAllUser();
//        System.out.println("list2= " + list2);
//
        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getById(1);
        u.setName("rovshan");
        userDao.updateUser(u);
//        User u = new User(0,"Sarkhan", "Rasullu", "994774441144", "khgkjbsash@gmail.com");
//        userDao.addUser(u);
    }
}
