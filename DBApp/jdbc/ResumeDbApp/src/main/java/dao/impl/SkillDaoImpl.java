package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.SkillDaoInter;
import entity.EmploymentHistory;
import entity.Skill;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {
    private Skill getSkill(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");

        Skill skill = new Skill(id,name);
        return skill;
    }
    @Override
    public List<Skill> getAllSkills() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from skill ");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill skill = getSkill(rs);
                result.add(skill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateSkill(Skill s) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update skill set name=? where id=?");
            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }

    @Override
    public Skill getById(int skillId) {
        Skill result = null;
        try (Connection c = connect();
             Statement stmt = c.createStatement()) {
            stmt.execute("select s.* from skill s where s.id=" + skillId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getSkill(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
