package ua.goIt.servlet.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Project;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.ProjectWebService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/project/edit/*")
public class EditProjectServlet extends HttpServlet {
    private ProjectWebService projectWebService;

    @Override
    public void init() throws ServletException {
        projectWebService = (ProjectWebService)getServletContext().getAttribute("projectWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
        Optional<Project> prj = projectWebService.findById(Long.parseLong(id)).stream().map(Project.class::cast).findFirst();
        if (prj.isPresent()) {
            Project project = prj.get();
            req.setAttribute("project", project);
            req.getRequestDispatcher("/edit_project.jsp").forward(req, resp);
        }
        resp.sendRedirect("/projects");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Project.class)
                .ifPresent(project -> projectWebService.update(project));
        resp.sendRedirect("/projects");
    }
}
