package ua.goit.controller.projects;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dto.CustomersAndCompaniesDTO;
import ua.goit.dto.DevelopersOnProjectsDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.customers.CustomersAndCompaniesService;
import ua.goit.service.developers.DevelopersOnProjectsService;
import ua.goit.service.projects.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private ProjectsRepository projectsRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;
    private CustomersAndCompaniesRepository customersAndCompaniesRepository;
    private ProjectService projectService;
    private DevelopersOnProjectsService developersOnProjectsService;
    private CustomersAndCompaniesService customersAndCompaniesService;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(DatabaseConnectionManager.getDataSource());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository(DatabaseConnectionManager.getDataSource());
        this.customersAndCompaniesRepository = new CustomersAndCompaniesRepository(DatabaseConnectionManager.getDataSource());
        this.projectService = new ProjectService(projectsRepository);
        this.developersOnProjectsService = new DevelopersOnProjectsService(developersOnProjectsRepository);
        this.customersAndCompaniesService = new CustomersAndCompaniesService(customersAndCompaniesRepository);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/projects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProjectsDTO projectsDTO = addProject(req);
        addDevelopersOnProject(req, projectsDTO);
        addCustomersAndCompanies(req, projectsDTO);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }

    private ProjectsDTO addProject(HttpServletRequest req) {
        ProjectsDTO projectsDTO = new ProjectsDTO();
        projectsDTO.setProjectName(req.getParameter("name"));
        projectsDTO.setStage(req.getParameter("stage"));
        projectsDTO.setTimePeriod(Integer.parseInt(req.getParameter("period")));
        projectsDTO.setCoast(Integer.parseInt(req.getParameter("coast")));
        projectsDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        projectsDTO.setDateOfBeginning(LocalDate.parse(req.getParameter("start date")));
        projectService.create(projectsDTO);
        return projectsDTO;
    }

    private void addDevelopersOnProject(HttpServletRequest req, ProjectsDTO projectsDTO) {
        if(!req.getParameter("developers").equals("")) {
            String[] s = req.getParameter("developers").split(",");
            Arrays.stream(s)
                    .map(Long::parseLong)
                    .map((d) -> {
                        DevelopersOnProjectsDTO developersOnProjectsDTO = new DevelopersOnProjectsDTO();
                        developersOnProjectsDTO.setProjectId(projectsDTO.getProjectId());
                        developersOnProjectsDTO.setDeveloperId(d);
                        return developersOnProjectsDTO;
                    })
                    .map(d -> developersOnProjectsRepository.findUniqueValue(d.getDeveloperId(), d.getProjectId()))
                    .forEach(d -> {
                        if (d.getDeveloperId() == 0 && d.getProjectId() == 0) {
                            developersOnProjectsRepository.create(d);
                        } else developersOnProjectsRepository.update(d);
                    });
        }
    }

    private void addCustomersAndCompanies(HttpServletRequest req, ProjectsDTO projectsDTO) {
        List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = createCustomersAndCompaniesList(req, projectsDTO);
        customersAndCompaniesDTOList.stream()
                .map((cc) ->
                    customersAndCompaniesRepository.findUniqueRecord(cc.getCompanyId(), cc.getCustomerId(), cc.getProjectId()))
                .forEach(c -> {
                    if(c.getCustomerId() == 0 && c.getProjectId() == 0 && c.getCompanyId() == 0) {
                        customersAndCompaniesRepository.create(c);
                    } else customersAndCompaniesRepository.update(c);
                });
    }

    private List<CustomersAndCompaniesDTO> createCustomersAndCompaniesList(HttpServletRequest req, ProjectsDTO projectsDTO) {

            List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = new ArrayList<>();
        if (!req.getParameter("customers").equals("") && !req.getParameter("companies").equals("")) {
            List<Long> customers = Arrays.stream(req.getParameter("customers").split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            List<Long> companies = Arrays.stream(req.getParameter("companies").split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            for (int i = 0; i < customers.size() || i < companies.size(); i++) {
                CustomersAndCompaniesDTO customersAndCompaniesDTO = new CustomersAndCompaniesDTO();
                customersAndCompaniesDTO.setProjectId(projectsDTO.getProjectId());
                if (i < customers.size()) {
                    customersAndCompaniesDTO.setCustomerId(customers.get(i));
                }
                if (i < companies.size()) {
                    customersAndCompaniesDTO.setCompanyId(companies.get(i));
                }
                customersAndCompaniesDTOList.add(customersAndCompaniesDTO);
            }
        }
        return customersAndCompaniesDTOList;
    }
}