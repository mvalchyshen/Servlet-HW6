package ua.goit.controller.developers;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.SkillsRepository;
import ua.goit.dao.model.Levels;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dao.model.Stack;
import ua.goit.dto.DevelopersDTO;
import ua.goit.dto.DevelopersOnProjectsDTO;
import ua.goit.dto.SkillsDTO;
import ua.goit.service.developers.DevelopersOnProjectsService;
import ua.goit.service.developers.DevelopersService;
import ua.goit.service.skills.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private DevelopersRepository developersRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;
    private SkillsRepository skillsRepository;
    private DevelopersService developersService;
    private DevelopersOnProjectsService developersOnProjectsService;
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(DatabaseConnectionManager.getDataSource());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository(DatabaseConnectionManager.getDataSource());
        this.skillsRepository = new SkillsRepository(DatabaseConnectionManager.getDataSource());
        this.developersService = new DevelopersService(developersRepository);
        this.developersOnProjectsService = new DevelopersOnProjectsService(developersOnProjectsRepository);
        this.skillsService = new SkillsService(skillsRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/developers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DevelopersDTO developersDTO = addDeveloper(req);
        addDevelopersOnProjects(req, developersDTO);
        addSkill(req, developersDTO);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }

    private DevelopersDTO addDeveloper(HttpServletRequest req) {
        DevelopersDTO developersDTO = new DevelopersDTO();
        developersDTO.setFirstName(req.getParameter("first name"));
        developersDTO.setLastName(req.getParameter("last name"));
        developersDTO.setGender(req.getParameter("gender"));
        developersDTO.setAge(Integer.parseInt(req.getParameter("age")));
        developersDTO.setExperienceInYears(Integer.parseInt(req.getParameter("experience")));
        developersDTO.setCompanyId(Integer.parseInt(req.getParameter("company")));
        developersDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        developersDTO.setDeveloperEmail(req.getParameter("email"));
        developersService.create(developersDTO);
        return developersDTO;
    }

    private void addDevelopersOnProjects(HttpServletRequest req, DevelopersDTO developersDTO) {
        if(!req.getParameter("projects").equals("")) {
            String[] s = req.getParameter("projects").split(",");
            List<Long> projectIds = Arrays.stream(s)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            List<DevelopersOnProjectsDTO> developersOnProjectsDTOListToAdd = projectIds.stream()
                    .map((p) -> {
                        DevelopersOnProjectsDTO developersOnProjectsDTO = new DevelopersOnProjectsDTO();
                        developersOnProjectsDTO.setDeveloperId(developersDTO.getDeveloperId());
                        developersOnProjectsDTO.setProjectId(p);
                        return developersOnProjectsDTO;
                    })
                    .filter((d) -> developersOnProjectsRepository.findUniqueValue(
                            d.getProjectId(), d.getDeveloperId()) == null)
                    .collect(Collectors.toList());

            developersOnProjectsDTOListToAdd.forEach(developersOnProjectsService::create);
        }
    }

    private void addSkill(HttpServletRequest req, DevelopersDTO developersDTO) {
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setDeveloperEmail(developersDTO.getDeveloperEmail());
        skillsDTO.setStack(Stack.valueOf(req.getParameter("stack")));
        skillsDTO.setLevel(Levels.valueOf(req.getParameter("level")));
        SkillsDAO skillsDAO = skillsRepository.findSkillsForDeveloperByEmail(
                developersDTO.getDeveloperEmail(), skillsDTO.getStack()
        );
        if(skillsDAO.getRecordId() == 0) {
            skillsService.create(skillsDTO);
        }else skillsService.update(skillsDTO);
    }
}
