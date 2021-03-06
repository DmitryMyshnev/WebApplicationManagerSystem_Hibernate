package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Customer;
import ua.goIt.model.Project;

import java.util.List;
import java.util.Optional;

public class CustomerDao extends AbstractDao<Customer>{
    private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);
    private static CustomerDao customerDao;

    private CustomerDao() {
    }

    @Override
    public Optional<Customer> getById(Long id) {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class,id);
            transaction.commit();
            return Optional.of(customer);
        }
        catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> getAll() {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery("FROM Customer", Customer.class).getResultList();
            transaction.commit();
            return customers;
        }
        catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            return List.of();
        }
    }

    @Override
    public void delete(Customer entity) {
        Session session;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
             transaction = session.beginTransaction();
            for (Project prj : entity.getProjects()) {
                prj.getCustomers().remove(entity);
            }
            session.delete(entity);
            transaction.commit();
        }
        catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }
    }

    public static CustomerDao getInstance(){
        if(customerDao == null){
            customerDao = new CustomerDao();
        }
        return customerDao;
    }
}
