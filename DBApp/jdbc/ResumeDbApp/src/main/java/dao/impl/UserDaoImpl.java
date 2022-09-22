package dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.inter.AbstractDAO;
import dao.inter.UserDaoInter;
import entity.Country;
import entity.User;

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

        return (new User(id, name, surname, phone, email, profileDescription, address, birthdate, nationality, birthplace));
    }

    private User getUserSimple(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        Date birthdate = rs.getDate("birthdate");


        User user = new User(id, name, surname, phone, email, profileDescription, address, birthdate, null, null);
        user.setPassword(rs.getString("password"));
        return user;
    }

    @Override
    public List<User> getAllUser(String name, String surname, Integer nationalityId, String address, String email, String phone, String profileDescription) {
        List<User> result = new ArrayList<>();

        try (Connection c = connect()) {
            String sql = "select u.*, n.nationality, c.name as birthplace from \"user\" u left join country n on u.nationality_id = n.id  left join country c on u.birthplace_id = c.id where 1 = 1 ";
            if (name != null && !(name.trim().isEmpty())) {
                sql += " and u.name = ? ";
            }
            if (surname != null && !(surname.trim().isEmpty())) {
                sql += "and u.surname = ? ";
            }
            if (nationalityId != null) {
                sql += "and u.nationality_id = ? ";
            }
            if (address != null) {
                sql += "and u.address = ?";
            }
            if (email != null) {
                sql += "and u.email = ?";
            }
            if (phone != null) {
                sql += "and u.phone = ?";
            }
            if (profileDescription != null) {
                sql += "and u.profile_description = ?";
            }
            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;//i indeksi bildirir
            if (name != null && !(name.trim().isEmpty())) {
                stmt.setString(i, name);
                i++;
            }
            if (surname != null && !(surname.trim().isEmpty())) {
                stmt.setString(i, surname);
                i++;
            }
            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
                i++;
            }
            if (address != null && !(address.trim().isEmpty())) {
                stmt.setString(i, address);
                i++;
            }
            if (email != null && !(email.trim().isEmpty())) {
                stmt.setString(i, email);
                i++;
            }
            if (phone != null && !(phone.trim().isEmpty())) {
                stmt.setString(i, phone);
                i++;
            }
            if (profileDescription != null && !(profileDescription.trim().isEmpty())) {
                stmt.setString(i, profileDescription);
            }
            stmt.execute();
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

//    public List<User> getAllUser(String name, String surname, String address, String email, String password, String phone, String profileDescription) {
//        List<User> result = new ArrayList<>();
//
//        try (Connection c = connect()) {
//            String sql = "select u.*, n.nationality, c.name as birthplace from \"user\" u left join country n on u.nationality_id = n.id  left join country c on u.birthplace_id = c.id where 1 = 1 ";
//            if (name != null && !(name.trim().isEmpty())) {
//                sql += " and u.name = ? ";
//            }
//            if (surname != null && !(surname.trim().isEmpty())) {
//                sql += "and u.surname = ? ";
//            }
//
//            if (address != null) {
//                sql += "and u.address = ?";
//            }
//            if (email != null) {
//                sql += "and u.email = ?";
//            }
//            if (email != null) {
//                sql += "and u.email = ?";
//            }
//            if (phone != null) {
//                sql += "and u.phone = ?";
//            }
//            if (profileDescription != null) {
//                sql += "and u.profile_description = ?";
//            }
//            PreparedStatement stmt = c.prepareStatement(sql);
//
//            int i = 1;//i indeksi bildirir
//            if (name != null && !(name.trim().isEmpty())) {
//                stmt.setString(i, name);
//                i++;
//            }
//            if (surname != null && !(surname.trim().isEmpty())) {
//                stmt.setString(i, surname);
//                i++;
//            }
//            if (nationalityId != null) {
//                stmt.setInt(i, nationalityId);
//                i++;
//            }
//            if (address != null && !(address.trim().isEmpty())) {
//                stmt.setString(i, address);
//                i++;
//            }
//            if (email != null && !(email.trim().isEmpty())) {
//                stmt.setString(i, email);
//                i++;
//            }
//            if (phone != null && !(phone.trim().isEmpty())) {
//                stmt.setString(i, phone);
//                i++;
//            }
//            if (profileDescription != null && !(profileDescription.trim().isEmpty())) {
//                stmt.setString(i, profileDescription);
//            }
//            stmt.execute();
//            ResultSet rs = stmt.getResultSet();
//
//            while (rs.next()) {
//                User u = getUser(rs);
//                result.add(u);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from \"user\" where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from \"user\" where email=? ");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByName(String name) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select  * from \"user\" where name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect();
             Statement stmt = c.createStatement()) {
            stmt.execute("select u.*,n.nationality ,c.name as birthplace  from \"user\" u left join country n on u.nationality_id = n.id left join country c on u.birthplace_id  =c.id where u.id=" + userId);
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
            PreparedStatement stmt = c.prepareStatement("update \"user\" set name=?,surname=?,phone=?, email=?,birthdate=?, birthplace_id=?,profile_description=?, address=?  where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setDate(5, u.getBirthDate());
            stmt.setInt(6, u.getBirthPlace().getId());
            stmt.setString(7, u.getProfileDescription());
            stmt.setString(8, u.getAddress());
            stmt.setInt(9, u.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        System.out.println("salam");
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into  \"user\" (name,surname,phone, email,password,profile_description, address) values(?,?,?,?,?,?,?) ");
            System.out.println();
//            stmt.setInt(1, u.getId());
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, crypt.hashToString(4, u.getPassword().toCharArray()));
            stmt.setString(6, u.getProfileDescription());
            stmt.setString(7, u.getAddress());
            return stmt.execute();
        } catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }

    public static void main(String[] args) {
//        User u = new User("test123", "test", "+994", "mail", null, null, null, null, null);
//        u.setPassword("12345");
//        new UserDaoImpl().addUser(u);

    }


    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect();
             Statement stmt = c.createStatement();
        ) {
            return stmt.execute("delete from \"user\"  where id= " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}



