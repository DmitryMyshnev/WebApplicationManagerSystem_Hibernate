package ua.goIt.servlet.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Project;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.ProjectWebService;
import ua.goIt.servlet.developer.RemoveDeveloperServlet;

import java.io.IOException;

@WebServlet("/project/new")
public class NewProjectServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(RemoveDeveloperServlet.class);
    private ProjectWebService projectWebService;
    @Override
    public void init() throws ServletException {
        projectWebService = (ProjectWebService) getServletContext().getAttribute("projectWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("project", new Project());
        req.getRequestDispatcher("/new_project.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Project.class)
                .ifPresent(project -> projectWebService.save(project));
        resp.sendRedirect("/projects");
    }
}
