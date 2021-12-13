package ua.goIt.servlet.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.goIt.services.webService.*;


@WebListener
public class LoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("developerService", DeveloperWebService.getInstance());
        servletContext.setAttribute("companyWebService", CompanyWebService.getInstance());
        servletContext.setAttribute("customerWebService", CustomerWebService.getInstance());
        servletContext.setAttribute("projectWebService", ProjectWebService.getInstance());
        servletContext.setAttribute("skillsWebServlet", SkillsWebService.getInstance());
    }
}
