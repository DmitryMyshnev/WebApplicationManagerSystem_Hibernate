package ua.goIt.servlet.developer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Developer;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.DeveloperWebService;

import java.io.IOException;

@WebServlet("/developer/new")
public class NewDeveloperServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(RemoveDeveloperServlet.class);
    private DeveloperWebService developerWebService;
    @Override
    public void init() throws ServletException {
        developerWebService = (DeveloperWebService) getServletContext().getAttribute("developerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("developer", new Developer());
        req.getRequestDispatcher("/new_developer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Developer.class)
                .ifPresent(dev -> developerWebService.save(dev));
        resp.sendRedirect("/developers");
    }
}
