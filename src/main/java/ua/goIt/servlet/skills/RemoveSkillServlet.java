package ua.goIt.servlet.skills;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Skill;
import ua.goIt.services.webService.SkillsWebService;

import java.io.IOException;

@WebServlet("/skill/remove/*")
public class RemoveSkillServlet extends HttpServlet {
    private SkillsWebService skillsWebService;

    @Override
    public void init() throws ServletException {
        skillsWebService = (SkillsWebService)getServletContext().getAttribute("skillsWebServlet");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
        skillsWebService.findById(Long.parseLong(id)).stream().map(Skill.class::cast).findFirst().ifPresent(skill->{
            skillsWebService.delete(skill);
        });
        resp.sendRedirect("/skills");
    }
}
