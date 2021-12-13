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
import java.util.Optional;

@WebServlet("/skill/edit/*")
public class EditSkillServlet extends HttpServlet {
    private SkillsWebService skillsWebService;

    @Override
    public void init() throws ServletException {
        skillsWebService = (SkillsWebService)getServletContext().getAttribute("skillsWebServlet");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestURI = req.getRequestURI().split("/");
        String id = requestURI[requestURI.length-1];
        Optional<Skill> prj = skillsWebService.findById(Long.parseLong(id)).stream().map(Skill.class::cast).findFirst();
        if (prj.isPresent()) {
            Skill skill = prj.get();
            req.setAttribute("skill", skill);
            req.getRequestDispatcher("/skill.jsp").forward(req, resp);
        }
        resp.sendRedirect("/skills");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Skill.class)
                .ifPresent(skill -> skillsWebService.update(skill));
        resp.sendRedirect("/skills");
    }
}
