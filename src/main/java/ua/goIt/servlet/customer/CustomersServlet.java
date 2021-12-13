package ua.goIt.servlet.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Customer;
import ua.goIt.services.webService.CustomerWebService;

import java.io.IOException;
import java.util.Comparator;

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(CustomersServlet.class);
    private CustomerWebService customerWebService;
    @Override
    public void init() throws ServletException {
        customerWebService = (CustomerWebService) getServletContext().getAttribute("customerWebService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object[] customers = customerWebService.getAll().stream().map(Customer.class::cast).sorted(Comparator.comparing(Customer::getId)).toArray();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/customers.jsp").include(req, resp);
    }
}
