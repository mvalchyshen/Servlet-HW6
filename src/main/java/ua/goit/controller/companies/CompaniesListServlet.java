package ua.goit.controller.companies;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dto.CompaniesDTO;
import ua.goit.service.companies.CompaniesConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/companies/list")
public class CompaniesListServlet extends HttpServlet {
    private CompaniesRepository companiesRepository;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(DatabaseConnectionManager.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompaniesDAO> companiesDAOList = companiesRepository.findAllCompanies();
        List<CompaniesDTO> companiesDTOList = companiesDAOList.stream()
                .map(CompaniesConverter::fromCompany)
                .collect(Collectors.toList());
        req.setAttribute("companies", companiesDTOList);
        req.getRequestDispatcher("/view/companies/listOfCompanies.jsp").forward(req, resp);
    }
}
