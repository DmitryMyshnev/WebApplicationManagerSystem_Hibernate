package ua.goIt.servlet.company;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Company;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.CompanyWebService;

import java.io.IOException;

@WebServlet("/company/new")
public class NewCompanyServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(NewCompanyServlet.class);
    private CompanyWebService companyWebService;
    @Override
    public void init() throws ServletException {
        companyWebService = (CompanyWebService) getServletContext().getAttribute("companyWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("company", new Company());
        req.getRequestDispatcher("/new_company.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Company.class)
                .ifPresent(company -> companyWebService.save(company));
        resp.sendRedirect("/companies");
    }
}
