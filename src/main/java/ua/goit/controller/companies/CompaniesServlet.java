package ua.goit.controller.companies;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dto.CompaniesDTO;
import ua.goit.dto.CustomersAndCompaniesDTO;
import ua.goit.service.companies.CompaniesService;
import ua.goit.service.customers.CustomersAndCompaniesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private CompaniesRepository companiesRepository;
    private CustomersAndCompaniesRepository customersAndCompaniesRepository;
    private CompaniesService companiesService;
    private CustomersAndCompaniesService customersAndCompaniesService;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(DatabaseConnectionManager.getDataSource());
        this.customersAndCompaniesRepository = new CustomersAndCompaniesRepository(DatabaseConnectionManager.getDataSource());
        this.companiesService = new CompaniesService(companiesRepository);
        this.customersAndCompaniesService = new CustomersAndCompaniesService(customersAndCompaniesRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/companies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompaniesDTO companiesDTO = addCompany(req);
        addCustomersAndCompanies(req, companiesDTO);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }

    private CompaniesDTO addCompany(HttpServletRequest req) {
        CompaniesDTO companiesDTO = new CompaniesDTO();
        companiesDTO.setCompanyName(req.getParameter("company name"));
        companiesDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        companiesService.create(companiesDTO);
        return companiesDTO;
    }

    private void addCustomersAndCompanies(HttpServletRequest req, CompaniesDTO companisDTO) {
        List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = createCustomersAndCompaniesList(req, companisDTO);
        customersAndCompaniesDTOList.forEach(customersAndCompaniesService::create);
    }

    private List<CustomersAndCompaniesDTO> createCustomersAndCompaniesList(HttpServletRequest req, CompaniesDTO companiesDTO) {
        List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = new ArrayList<>();
        if(!req.getParameter("customers").equals("") && !req.getParameter("projects").equals("")) {
            List<Long> customers = Arrays.stream(req.getParameter("customers").split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            List<Long> projects = Arrays.stream(req.getParameter("projects").split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            for (int i = 0; i < customers.size() || i < projects.size(); i++) {
                CustomersAndCompaniesDTO customersAndCompaniesDTO = new CustomersAndCompaniesDTO();
                customersAndCompaniesDTO.setProjectId(companiesDTO.getCompanyId());
                if (i < customers.size()) {
                    customersAndCompaniesDTO.setCustomerId(customers.get(i));
                }
                if (i < projects.size()) {
                    customersAndCompaniesDTO.setCompanyId(projects.get(i));
                }
                customersAndCompaniesDTOList.add(customersAndCompaniesDTO);
            }
        }
        return customersAndCompaniesDTOList;
    }
}
