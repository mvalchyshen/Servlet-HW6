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

@WebServlet("/developers/developer")
public class FindByIdServlet extends HttpServlet {
    private DevelopersRepository developersRepository;

    @Override
    public void init() {
        this.developersRepository = new DevelopersRepository(DatabaseConnectionManager.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DevelopersDAO developersDAO = developersRepository.findById(Long.parseLong(id));
        if(developersDAO.getDeveloperId() == 0){
            ServletException ex = new ServletException("The developer does not exist");
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }else {
            DevelopersDTO developersDTO = DevelopersConverter.fromDeveloper(developersDAO);
            req.setAttribute("developer", developersDTO);
            req.getRequestDispatcher("/view/developers/findDeveloperById.jsp").forward(req, resp);
        }
    }
}
