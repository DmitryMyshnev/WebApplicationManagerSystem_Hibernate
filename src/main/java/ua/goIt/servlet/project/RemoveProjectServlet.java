package ua.goIt.servlet.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.model.Project;
import ua.goIt.services.webService.ProjectWebService;

import java.io.IOException;

@WebServlet("/project/remove/*")
public class RemoveProjectServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(RemoveProjectServlet.class);
    private ProjectWebService projectWebService;
    @Override
    public void init() throws ServletException {
        projectWebService = (ProjectWebService) getServletContext().getAttribute("projectWebService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
         projectWebService.findById(Long.parseLong(id)).stream().map(Project.class::cast).findFirst().ifPresent(project->{
             projectWebService.delete(project);
         });
        resp.sendRedirect("/projects");
    }

}
