package dao.inter;

import entity.Skill;
import entity.User;

import java.util.List;

public interface SkillDaoInter {
    List<Skill> getAllSkills();
    public boolean updateSkill(Skill s);
    public Skill getById(int id);
}
