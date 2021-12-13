package ua.goIt.servlet.developer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Developer;
import ua.goIt.services.webService.DeveloperWebService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/developer/*")
public class DeveloperServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(EditDeveloperServlet.class);
    private DeveloperWebService developerWebService;

    @Override
    public void init() throws ServletException {
        developerWebService = (DeveloperWebService) getServletContext().getAttribute("developerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
        Optional<Developer> userOpt = developerWebService.findById(Long.parseLong(id)).stream().map(Developer.class::cast).findFirst();
        if (userOpt.isPresent()) {
            Developer developer = userOpt.get();
            req.setAttribute("developer", developer);
            req.getRequestDispatcher("/developer.jsp").forward(req, resp);
        }
        resp.sendRedirect("/developers");
    }
}
