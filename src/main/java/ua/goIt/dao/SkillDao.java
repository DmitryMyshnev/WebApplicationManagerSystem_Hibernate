package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Skill;
import java.util.List;
import java.util.Optional;

public class SkillDao extends AbstractDao<Skill>{
    private static final Logger LOGGER = LogManager.getLogger(SkillDao.class);
    private static SkillDao skillDao;

    private SkillDao() {
    }

    @Override
    public Optional<Skill> getById(Long id) {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            Skill skill =session.get(Skill.class,id);
            transaction.commit();
            return Optional.of(skill);
        }
        catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> getAll() {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            List<Skill> skills = session.createQuery("FROM Skill", Skill.class).getResultList();
            transaction.commit();
            return skills;
        }
        catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            return List.of();
        }
    }

    public static SkillDao getInstance(){
        if(skillDao == null){
            skillDao = new SkillDao();
        }
        return skillDao;
    }
}
