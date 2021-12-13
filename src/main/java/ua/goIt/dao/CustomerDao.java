package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Customer;
import java.util.List;
import java.util.Optional;

public class CustomerDao extends AbstractDao<Customer>{
    private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);
    private static CustomerDao customerDao;

    private CustomerDao() {
    }

    @Override
    public Optional<Customer> getById(Long id) {
        Customer customer = HibernateUtil.getSession().get(Customer.class,id);
        return Optional.of(customer);
    }

    @Override
    public List<Customer> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> customers = HibernateUtil.getSession().createQuery("FROM Customer", Customer.class).getResultList();
        transaction.commit();
        session.close();
        return customers;
    }

    public static CustomerDao getInstance(){
        if(customerDao == null){
            customerDao = new CustomerDao();
        }
        return customerDao;
    }
}
