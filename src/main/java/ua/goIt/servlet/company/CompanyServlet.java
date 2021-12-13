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
import java.util.Optional;

@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(CompaniesServlet.class);
    private CompanyWebService companyWebService;
    @Override
    public void init() throws ServletException {
        companyWebService = (CompanyWebService) getServletContext().getAttribute("companyWebService");

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
        Optional<Company> comp = companyWebService.findById(Long.parseLong(id)).stream().map(Company.class::cast).findFirst();
        if (comp.isPresent()) {
            Company company = comp.get();
            req.setAttribute("company", company);
            req.getRequestDispatcher("/company.jsp").forward(req, resp);
        }
        resp.sendRedirect("/companies.jsp");
    }
}
