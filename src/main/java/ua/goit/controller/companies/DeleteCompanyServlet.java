package ua.goit.controller.companies;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dao.model.CustomersAndCompaniesDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/companies/deleteCompany")
public class DeleteCompanyServlet extends HttpServlet {
    private CompaniesRepository companiesRepository;
    CustomersAndCompaniesRepository customersAndCompaniesRepository;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(DatabaseConnectionManager.getDataSource());
        this.customersAndCompaniesRepository = new CustomersAndCompaniesRepository((DatabaseConnectionManager.getDataSource()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CompaniesDAO companiesDAOForDelete = companiesRepository.findById(Long.parseLong(id));
        List<CustomersAndCompaniesDAO> customersAndCompaniesDAOList = customersAndCompaniesRepository.findForCompany(
                companiesDAOForDelete.getCompanyId());
        if(customersAndCompaniesDAOList.size() > 0) {
            customersAndCompaniesRepository.deleteForCompany(companiesDAOForDelete.getCompanyId());
        }
        companiesRepository.delete(companiesDAOForDelete.getCompanyName());
        req.setAttribute("id", companiesDAOForDelete.getCompanyId());
        req.getRequestDispatcher("/view/companies/deleteCompany.jsp").forward(req, resp);
    }
}
