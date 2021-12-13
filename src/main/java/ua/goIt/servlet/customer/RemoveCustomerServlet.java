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

@WebServlet("/customer/remove/*")
public class RemoveCustomerServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(RemoveCustomerServlet.class);
    private CustomerWebService customerWebService;
    @Override
    public void init() throws ServletException {
        customerWebService = (CustomerWebService) getServletContext().getAttribute("customerWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
         customerWebService.findById(Long.parseLong(id)).stream().map(Customer.class::cast).findFirst().ifPresent(customer->{
             customerWebService.delete(customer);
         });
        resp.sendRedirect("/customers");
    }

}
