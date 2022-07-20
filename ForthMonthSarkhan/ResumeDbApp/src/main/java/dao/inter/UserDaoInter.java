package dao.inter;

import bean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDaoInter {
    List<User> getAllUser() throws Exception;
    public User getById(int id);
    public boolean updateUser(User u);
    public boolean addUser(User u);
    public boolean removeUser(int id);
}
