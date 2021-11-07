package ua.goit.controller.projects;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.projects.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/project")
public class ProjectByNameServlet extends HttpServlet {

    private ProjectsRepository projectsRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;
    private CustomersAndCompaniesRepository customersAndCompaniesRepository;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(DatabaseConnectionManager.getDataSource());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository(DatabaseConnectionManager.getDataSource());
        this.customersAndCompaniesRepository = new CustomersAndCompaniesRepository(DatabaseConnectionManager.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectsDAO projectsDAO = projectsRepository.findByUniqueValue(name);
        if(projectsDAO.getProjectId() == 0){
            ServletException ex = new ServletException("The project does not exist");
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }else {
            ProjectsDTO project = ProjectsConverter.fromProject(projectsDAO);
            req.setAttribute("project", project);
            req.getRequestDispatcher("/view/projects/findProjectByName.jsp").forward(req, resp);
        }
    }
}
