package ua.goIt.servlet.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Project;
import ua.goIt.services.webService.ProjectWebService;

import java.io.IOException;
import java.util.Comparator;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private ProjectWebService projectWebService;

    @Override
    public void init() throws ServletException {
        projectWebService = (ProjectWebService)getServletContext().getAttribute("projectWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object[] project = projectWebService.getAll().stream().map(Project.class::cast).sorted(Comparator.comparing(Project::getId)).toArray();
        req.setAttribute("projects", project);
        req.getRequestDispatcher("projects.jsp").include(req, resp);
    }

}
