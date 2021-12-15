package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Project;
import java.util.List;
import java.util.Optional;

public class ProjectDao extends AbstractDao<Project>{
    private static ProjectDao projectDao;
    private static final Logger LOGGER = LogManager.getLogger(ProjectDao.class);

    private ProjectDao() {
    }

    @Override
    public Optional<Project> getById(Long id) {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
       try (session){
            transaction = session.beginTransaction();
           Project project = session.get(Project.class,id);
           transaction.commit();
           return Optional.of(project);
       }
       catch (HibernateException e) {
           if (transaction != null)
               transaction.rollback();
           return Optional.empty();
       }
    }

    @Override
    public List<Project> getAll() {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
       try(session) {
            transaction = session.beginTransaction();
           List<Project> projects = session.createQuery("FROM Project", Project.class).getResultList();
           transaction.commit();
           return projects;
       }
       catch (HibernateException e){
           if(transaction != null)
               transaction.rollback();
           return List.of();
       }
    }

    public static ProjectDao getInstance(){
        if(projectDao == null){
            projectDao = new ProjectDao();
        }
        return projectDao;
    }
}
