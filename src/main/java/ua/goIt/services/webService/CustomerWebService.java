package ua.goIt.services.webService;

import ua.goIt.dao.CustomerDao;
import ua.goIt.dao.ProjectDao;
import ua.goIt.model.Customer;
import ua.goIt.model.Project;

import java.util.List;
import java.util.Optional;

public class CustomerWebService implements CrudWeb<Customer> {
    private static CustomerWebService customerWebService;
    private final CustomerDao customerDao;
    private final ProjectDao projectDao;

    private CustomerWebService() {
        customerDao = CustomerDao.getInstance();
        projectDao = ProjectDao.getInstance();
    }

    @Override
    public void save(Customer entity) {
        customerDao.create(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDao.update(entity);
    }

    @Override
    public void delete(Customer entity) {
        customerDao.delete(entity);
    }

    @Override
    public List<Customer> getAll() {
        return  customerDao.getAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerDao.getById(id);
    }


    public static CustomerWebService getInstance() {
        if (customerWebService == null) {
            customerWebService = new CustomerWebService();
        }
        return customerWebService;
    }
}
