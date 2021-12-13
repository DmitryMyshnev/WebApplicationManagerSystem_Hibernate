package ua.goIt.servlet.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Customer;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.CustomerWebService;

import java.io.IOException;

@WebServlet("/customer/new")
public class NewCustomerServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(NewCustomerServlet.class);
    private CustomerWebService customerWebService;
    @Override
    public void init() throws ServletException {
        customerWebService = (CustomerWebService) getServletContext().getAttribute("customerWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customer", new Customer());
        req.getRequestDispatcher("/new_customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Customer.class)
                .ifPresent(customer -> customerWebService.save(customer));
        resp.sendRedirect("/customers");
    }
}
