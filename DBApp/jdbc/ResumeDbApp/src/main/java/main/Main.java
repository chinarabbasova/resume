package main;

import dao.inter.UserDaoInter;
import entity.User;

public class Main {
    public static void main(String[] args) throws Exception {
//        List<User> list =  userDao.getAllUser();
//        System.out.println("list= " + list);
//        userDao.removeUser(2);
//       List<User> list2 =  userDao.getAllUser();
//        System.out.println("list2= " + list2);

//        CountryDaoInter dao = Context.instanceCountryDao();
//        System.out.println(dao.getAllCountries());
//        UserDaoInter dao = Context.instanceUserDao();
//        System.out.println(dao.getById(1));
//
//        SkillDaoInter dao2 = Context.instanceSkillDao();
//        System.out.println(dao2.getAllSkills());


//        EmployementHistoryDaoInter dao = Context.instanceEmploymentHistoryDao();
//        System.out.println(dao.getAllEmploymentHistoryByUserId(5));


        UserDaoInter userDao = Context.instanceUserDao();
//      System.out.println(userDao.getAllUser());
        User u = userDao.getById(1);
        u.setName("nazim");
        userDao.updateUser(u);
//        User u = new User(0,"Sarkhan", "Rasullu", "994774441144", "khgkjbsash@gmail.com");
//        userDao.addUser(u);
    }
}
