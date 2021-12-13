package ua.goIt.servlet.skills;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goIt.model.Skill;
import ua.goIt.services.HandleBodyUtil;
import ua.goIt.services.webService.SkillsWebService;

import java.io.IOException;

@WebServlet("/skill/new")
public class NewSkillServlet extends HttpServlet {
    private SkillsWebService skillsWebService;

    @Override
    public void init() throws ServletException {
        skillsWebService = (SkillsWebService)getServletContext().getAttribute("skillsWebServlet");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("skill", new Skill());
        req.getRequestDispatcher("/new_skill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Skill.class)
                .ifPresent(skill -> skillsWebService.save(skill));
        resp.sendRedirect("/skills");
    }
}
