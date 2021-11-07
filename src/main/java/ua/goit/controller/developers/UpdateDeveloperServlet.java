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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/developers/update")
public class UpdateDeveloperServlet extends HttpServlet {
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
        String email = req.getParameter("email");
        DevelopersDTO developersDTO = developersService.findByEmail(email);
        SkillsDTO skillsDTO = skillsService.findByDeveloper(email);
        List<DevelopersOnProjectsDTO> developersOnProjectsDTOList = developersOnProjectsService.findByProjectId(developersDTO.getDeveloperId());
        String s = "";
        String projects ="";
        if(developersOnProjectsDTOList.size() != 0) {
            projects = developersOnProjectsDTOList.stream()
                    .map(DevelopersOnProjectsDTO::getProjectId)
                    .map(d -> s.concat(String.valueOf(d)).concat(","))
            .collect(Collectors.joining());
        }
        req.setAttribute("developer", developersDTO);
        req.setAttribute("skillsDTO", skillsDTO);
        req.setAttribute("projectsList", projects);
        req.getRequestDispatcher("/view/developers/updateDeveloperForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DevelopersDTO developersDTO = updateDeveloper(req);
        updateSkills(req, developersDTO);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }

    private DevelopersDTO updateDeveloper(HttpServletRequest req) {
        DevelopersDTO developersDTO = new DevelopersDTO();
        developersDTO.setFirstName(req.getParameter("first name"));
        developersDTO.setLastName(req.getParameter("last name"));
        developersDTO.setGender(req.getParameter("gender"));
        developersDTO.setAge(Integer.parseInt(req.getParameter("age")));
        developersDTO.setExperienceInYears(Integer.parseInt(req.getParameter("experience")));
        developersDTO.setCompanyId(Integer.parseInt(req.getParameter("company")));
        developersDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        developersDTO.setDeveloperEmail(req.getParameter("email"));
        developersService.update(developersDTO);
        return developersDTO;
    }

    private void updateSkills(HttpServletRequest req, DevelopersDTO developersDTO) {
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
