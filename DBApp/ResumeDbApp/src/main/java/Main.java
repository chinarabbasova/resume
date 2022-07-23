import dao.impl.UserDaoImpl;
import dao.inter.UserDaoInter;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = new UserDaoImpl();
        System.out.println(userDao.getAllUser());
//        List<User> list =  userDao.getAllUser();
//        System.out.println("list= " + list);
//        userDao.removeUser(2);
//       List<User> list2 =  userDao.getAllUser();
//
//        System.out.println("list2= " + list2);
    }
}
