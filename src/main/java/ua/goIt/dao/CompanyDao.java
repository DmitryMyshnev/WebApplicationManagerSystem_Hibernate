package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Company;
import ua.goIt.model.Project;

import java.util.List;
import java.util.Optional;

public class CompanyDao extends AbstractDao<Company>{
    private static final Logger LOGGER = LogManager.getLogger(CompanyDao.class);
    private static CompanyDao companyDao;

    private CompanyDao() {
    }

    @Override
    public Optional<Company> getById(Long id) {
        Session session;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
            Transaction transaction = session.beginTransaction();
            Company company = session.get(Company.class,id);
            transaction.commit();
            return Optional.of(company);
        }

    }

    @Override
    public List<Company> getAll() {
        Session session;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
            Transaction transaction = session.beginTransaction();
            List<Company> companies = session.createQuery("FROM Company", Company.class).getResultList();
            transaction.commit();
            return companies;
        }
    }

    @Override
    public void delete(Company entity) {
        Session session;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        try (session){
            Transaction transaction = session.beginTransaction();
            for (Project prj : entity.getProjects()) {
                prj.getCompanies().remove(entity);
            }
            session.delete(entity);
            transaction.commit();
        }
    }

    public static CompanyDao getInstance(){
        if(companyDao == null){
            companyDao = new CompanyDao();
        }
        return companyDao;
    }
}
