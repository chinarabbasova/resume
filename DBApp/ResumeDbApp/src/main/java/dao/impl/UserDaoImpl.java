package dao.impl;

import entity.Country;
import entity.Skill;
import entity.User;
import entity.UserSkill;
import dao.inter.AbstractDAO;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {
    private User getUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return (new User(id, name, surname, phone, email,profileDescription,address, birthdate, nationality, birthplace));
    }

    @Override
    public List<User> getAllUser() {
        List<User> result = new ArrayList<>();

        try (Connection c = connect();
             Statement stmt = c.createStatement()) {
            stmt.execute("select u.*,n.nationality ,c.name as birthplace  from user u \n" +
                    "    left join country n on u.nationality_id  =n.id \n" +
                    "      left join country c on u.birthplace_id  =c.id");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect();
             Statement stmt = c.createStatement()) {
            stmt.execute("select u.*,n.nationality ,c.name as birthplace  from user u " +
                    "left join country n on u.nationality_id  =n.id left join country c on u.birthplace_id  =c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                result = getUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?, email=?,birthdate=?, birthplace_id=?,profile_description=?, address=?  where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setDate(5, u.getBirthDate());
            stmt.setInt(6, u.getBirthPlace().getId());
            stmt.setString(7, u.getProfileDescription());
            stmt.setString(8,u.getAddress());
            stmt.setInt(9, u.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user (name,surname,phone, email,profile_description, address) values(?,?,?,?,?,?) ");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDescription());
            stmt.setString(6, u.getAddress());
            return stmt.execute();
        } catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }


    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect();
             Statement stmt = c.createStatement();
        ) {
            return stmt.execute("delete from user  where id= " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}



