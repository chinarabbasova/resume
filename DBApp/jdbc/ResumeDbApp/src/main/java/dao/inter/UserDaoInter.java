package dao.inter;

import entity.User;

import java.util.List;

public interface UserDaoInter {
    List<User> getAllUser(String name, String surname, Integer nationalityId,String address, String email, String phone, String profileDescription);
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
    public User findByName(String name);
    public User getById(int id);
    public boolean updateUser(User u);
    public boolean addUser(User u);
    public boolean removeUser(int id);


}
