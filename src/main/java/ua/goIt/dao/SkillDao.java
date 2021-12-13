package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goIt.DbStatement;
import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Company;
import ua.goIt.model.Project;
import ua.goIt.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillDao extends AbstractDao<Skill>{
    private static final Logger LOGGER = LogManager.getLogger(SkillDao.class);
    private static SkillDao skillDao;

    private SkillDao() {
    }

    @Override
    public Optional<Skill> getById(Long id) {
        Skill skill = HibernateUtil.getSession().get(Skill.class,id);
        return Optional.of(skill);
    }

    @Override
    public List<Skill> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Skill> skills = HibernateUtil.getSession().createQuery("FROM Skill", Skill.class).getResultList();
        transaction.commit();
        session.close();
        return skills;
    }


    public static SkillDao getInstance(){
        if(skillDao == null){
            skillDao = new SkillDao();
        }
        return skillDao;
    }
}
