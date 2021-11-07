package ua.goit.controller.projects;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.model.CustomersAndCompaniesDAO;
import ua.goit.dao.model.DevelopersOnProjectsDAO;
import ua.goit.dao.model.ProjectsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/projects/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
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
        ProjectsDAO projectsDAOForDelete = projectsRepository.findByUniqueValue(name);
        long projectId = projectsDAOForDelete.getProjectId();
        List<DevelopersOnProjectsDAO> toDeleteDevelopersOnProject = (developersOnProjectsRepository.findByProject(projectId)).stream()
        .filter((p) -> p.getProjectId() == projectId)
        .collect(Collectors.toList());
        if (toDeleteDevelopersOnProject.size() > 0) {
            developersOnProjectsRepository.deleteForProjects(projectId);
        }
        List<CustomersAndCompaniesDAO> customersAndCompaniesDAOList = customersAndCompaniesRepository.findForProject(projectId);
        List<CustomersAndCompaniesDAO> toDeleteCustomersAndCompanies = customersAndCompaniesDAOList.stream()
                .filter((c) -> c.getProjectId() == projectId)
                .collect(Collectors.toList());
        if(toDeleteCustomersAndCompanies.size() > 0) {
            customersAndCompaniesRepository.deleteForProject(projectId);
        }
        projectsRepository.delete(name);
        req.setAttribute("name", projectsDAOForDelete.getProjectName());
        req.getRequestDispatcher("/view/projects/deleteProject.jsp").forward(req, resp);
    }

}
