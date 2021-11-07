package ua.goit.controller.developers;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dto.DevelopersDTO;
import ua.goit.service.developers.DevelopersConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/developers/list")
public class DevelopersListServlet extends HttpServlet {
    private DevelopersRepository developersRepository;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(DatabaseConnectionManager.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DevelopersDAO> developersDAOList = developersRepository.findAll();
        List<DevelopersDTO> developersDTOList = developersDAOList.stream()
                .map(DevelopersConverter::fromDeveloper)
                .collect(Collectors.toList());
        req.setAttribute("developers", developersDTOList);
        req.getRequestDispatcher("/view/developers/listOfDevelopers.jsp").forward(req, resp);
    }
}
