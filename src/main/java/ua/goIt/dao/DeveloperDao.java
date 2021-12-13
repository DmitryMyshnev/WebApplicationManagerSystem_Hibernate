package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Developer;
import ua.goIt.model.Project;
import ua.goIt.model.Skill;
import java.util.List;
import java.util.Optional;

public class DeveloperDao extends AbstractDao<Developer> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);
    private static DeveloperDao developerDao;

    private DeveloperDao() {
    }

    @Override
    public Optional<Developer> getById(Long id) {
        Developer developer = HibernateUtil.getSession().get(Developer.class,id);
        return Optional.of(developer);
    }

    @Override
    public List<Developer> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Developer> developers = HibernateUtil.getSession().createQuery("FROM Developer", Developer.class).getResultList();
        transaction.commit();
        session.close();
        return  developers;
    }

    @Override
    public void delete(Developer entity) {
        try ( Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            for (Project prj : entity.getProjects()) {
                prj.getDevelopers().remove(entity);
            }
            for (Skill skill : entity.getSkills()) {
                skill.getDevelopers().remove(entity);
            }
            session.delete(entity);
            transaction.commit();
        }
        catch (HibernateException e){
           e.printStackTrace();
        }
    }

    public static DeveloperDao getInstance(){
        if(developerDao == null){
            developerDao = new DeveloperDao();
        }
        return developerDao;
   }
}
