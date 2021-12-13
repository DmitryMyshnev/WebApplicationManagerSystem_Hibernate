package ua.goIt.servlet.skills;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Skill;
import ua.goIt.services.webService.SkillsWebService;

import java.io.IOException;
import java.util.Comparator;

@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {
    private SkillsWebService skillsWebService;

    @Override
    public void init() throws ServletException {
        skillsWebService = (SkillsWebService)getServletContext().getAttribute("skillsWebServlet");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object[] project = skillsWebService.getAll().stream().map(Skill.class::cast).sorted(Comparator.comparing(Skill::getId)).toArray();
        req.setAttribute("skills", project);
        req.getRequestDispatcher("skills.jsp").include(req, resp);
    }
}
