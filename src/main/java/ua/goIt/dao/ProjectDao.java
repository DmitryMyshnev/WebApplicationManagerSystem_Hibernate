package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goIt.DbStatement;
import ua.goIt.config.HibernateUtil;
import ua.goIt.model.Company;
import ua.goIt.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDao extends AbstractDao<Project>{
    private static ProjectDao projectDao;
    private static final Logger LOGGER = LogManager.getLogger(ProjectDao.class);

    private ProjectDao() {
    }

    @Override
    public Optional<Project> getById(Long id) {
        Project project = HibernateUtil.getSession().get(Project.class,id);
        return Optional.of(project);
    }

    @Override
    public List<Project> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List<Project> projects = HibernateUtil.getSession().createQuery("FROM Project", Project.class).getResultList();
        transaction.commit();
        session.close();
        return projects;
    }

    public static ProjectDao getInstance(){
        if(projectDao == null){
            projectDao = new ProjectDao();
        }
        return projectDao;
    }
}
