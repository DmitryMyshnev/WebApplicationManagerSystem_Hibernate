package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goIt.DbStatement;
import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Company;
import ua.goIt.model.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyDao extends AbstractDao<Company>{
    private static final Logger LOGGER = LogManager.getLogger(CompanyDao.class);
    private static CompanyDao companyDao;

    private CompanyDao() {
    }

    @Override
    public Optional<Company> getById(Long id) {
        Company company = HibernateUtil.getSession().get(Company.class,id);
        return Optional.of(company);
    }

    @Override
    public List<Company> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Company> companies = HibernateUtil.getSession().createQuery("FROM Company", Company.class).getResultList();
        transaction.commit();
        session.close();
        return companies;
    }

    public static CompanyDao getInstance(){
        if(companyDao == null){
            companyDao = new CompanyDao();
        }
        return companyDao;
    }
}
