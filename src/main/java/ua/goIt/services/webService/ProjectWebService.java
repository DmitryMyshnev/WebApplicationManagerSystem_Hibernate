package ua.goIt.services.webService;

import ua.goIt.dao.CompanyDao;
import ua.goIt.dao.CustomerDao;
import ua.goIt.dao.DeveloperDao;
import ua.goIt.dao.ProjectDao;
import ua.goIt.model.Project;

import java.util.List;
import java.util.Optional;

public class ProjectWebService implements CrudWeb<Project> {
    private static ProjectWebService projectWebService;
    private final ProjectDao projectDao;
    private final DeveloperDao developerDao;
    private final CompanyDao companyDao;
    private final CustomerDao customerDao;

    private ProjectWebService() {
        projectDao =  ProjectDao.getInstance();
        developerDao = DeveloperDao.getInstance();
        companyDao = CompanyDao.getInstance();
        customerDao = CustomerDao.getInstance();
    }

    @Override
    public void save(Project entity) {
        projectDao.create(entity);
    }

    @Override
    public void update(Project entity) {
        projectDao.update(entity);
    }

    @Override
    public void delete(Project entity) {
        projectDao.delete(entity);
    }

    @Override
    public List<Project> getAll() {

  return projectDao.getAll();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectDao.getById(id);
    }

    public static ProjectWebService getInstance() {
        if (projectWebService == null) {
            projectWebService = new ProjectWebService();
        }
        return projectWebService;
    }
}
