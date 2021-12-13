package ua.goIt.servlet.company;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Company;
import ua.goIt.services.webService.CompanyWebService;

import java.io.IOException;
import java.util.Comparator;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(CompaniesServlet.class);
    private CompanyWebService companyWebService;
    @Override
    public void init() throws ServletException {
        companyWebService = (CompanyWebService) getServletContext().getAttribute("companyWebService");

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object[] company = companyWebService.getAll().stream().map(Company.class::cast).sorted(Comparator.comparing(Company::getId)).toArray();
        req.setAttribute("companies", company);
        req.getRequestDispatcher("/companies.jsp").include(req, resp);
    }
}
