package ua.goIt.servlet.company;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Company;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.CompanyWebService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/company/edit/*")
public class EditCompanyServlet extends HttpServlet {
    private CompanyWebService companyWebService;

    @Override
    public void init() throws ServletException {
        companyWebService = (CompanyWebService)getServletContext().getAttribute("companyWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
        Optional<Company> comp = companyWebService.findById(Long.parseLong(id)).stream().map(Company.class::cast).findFirst();
        if (comp.isPresent()) {
            Company company = comp.get();
            req.setAttribute("company", company);
            req.getRequestDispatcher("/edit_company.jsp").forward(req, resp);
        }
        resp.sendRedirect("/companies");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Company.class)
                .ifPresent(company -> companyWebService.update(company));
        resp.sendRedirect("/companies");
    }
}
