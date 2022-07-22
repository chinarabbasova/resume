import bean.User;
import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = new UserDaoImpl();
        List<User> list =  userDao.getAllUser();
        System.out.println("list= " + list);
        userDao.removeUser(2);
       List<User> list2 =  userDao.getAllUser();

        System.out.println("list2= " + list2);
    }
}
