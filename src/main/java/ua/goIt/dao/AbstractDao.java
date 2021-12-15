package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Identity;

abstract public class AbstractDao<T extends Identity> implements Dao<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    @Override
    public void create(T entity) {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
        catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void update(T entity) {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
        catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void delete(T entity) {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
        catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }
    }
}
