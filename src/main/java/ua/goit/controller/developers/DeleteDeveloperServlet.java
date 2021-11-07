package ua.goit.controller.developers;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dao.model.DevelopersOnProjectsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/developers/deleteDeveloper")
public class DeleteDeveloperServlet extends HttpServlet {
    private DevelopersRepository developersRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(DatabaseConnectionManager.getDataSource());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository((DatabaseConnectionManager.getDataSource()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DevelopersDAO developersDAOForDelete = developersRepository.findById(Long.parseLong(id));
        List<DevelopersOnProjectsDAO> developersOnProjectsDAOList = developersOnProjectsRepository.findByDeveloper(
                developersDAOForDelete.getDeveloperId());
        if(developersOnProjectsDAOList.size() > 0) {
            developersOnProjectsRepository.deleteForDevelopers(developersDAOForDelete.getDeveloperId());
        }
        developersRepository.deleteById(Long.parseLong(id));
        req.setAttribute("id", developersDAOForDelete.getDeveloperId());
        req.getRequestDispatcher("/view/developers/deleteDeveloper.jsp").forward(req, resp);
    }
}
