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

@WebServlet("/companies/update")
public class UpdateCompanyServlet extends HttpServlet {
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
        String name = req.getParameter("name");
        CompaniesDTO companiesDTO = companiesService.findByName(name);
        List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = customersAndCompaniesService.findByCompanyId(companiesDTO.getCompanyId());
        String s = "";
        String projects = "";
        String customers = "";
        if(customersAndCompaniesDTOList.size() != 0) {
            projects = customersAndCompaniesDTOList.stream()
                    .map(CustomersAndCompaniesDTO::getProjectId)
                    .map(comp -> s.concat(String.valueOf(comp)).concat(","))
            .collect(Collectors.joining());
            customers = customersAndCompaniesDTOList.stream()
                    .map(CustomersAndCompaniesDTO::getCustomerId)
                    .map(cust -> s.concat(String.valueOf(cust)).concat(","))
            .collect(Collectors.joining());
        }
        req.setAttribute("company", companiesDTO);
        req.setAttribute("projects", projects);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/view/companies/updateCompanyForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CompaniesDTO companiesDTO = updateCompany(req);
        updateCustomersAndCompanies(req, companiesDTO);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }

    private CompaniesDTO updateCompany(HttpServletRequest req) {
        CompaniesDTO companiesDTO = new CompaniesDTO();
        companiesDTO.setCompanyName(req.getParameter("company name"));
        companiesDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        companiesService.update(companiesDTO);
        return companiesDTO;
    }

    private void updateCustomersAndCompanies(HttpServletRequest req, CompaniesDTO companisDTO) {
        List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = createCustomersAndCompaniesList(req, companisDTO);
        if(customersAndCompaniesDTOList.size() > 0) {
            customersAndCompaniesDTOList.forEach(customersAndCompaniesService::update);
        }
    }

    private List<CustomersAndCompaniesDTO> createCustomersAndCompaniesList(HttpServletRequest req, CompaniesDTO companiesDTO) {
        List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = new ArrayList<>();
        List<Long> customers = new ArrayList<>();
        List<Long> projects = new ArrayList<>();
        if(!req.getParameter("customers").equals("")) {
            customers = Arrays.stream(req.getParameter("customers").split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
        if(!req.getParameter("projects").equals("")) {
            projects = Arrays.stream(req.getParameter("projects").split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
        for (int i = 0; i < customers.size() || i< projects.size(); i++) {
            CustomersAndCompaniesDTO customersAndCompaniesDTO = new CustomersAndCompaniesDTO();
            customersAndCompaniesDTO.setProjectId(companiesDTO.getCompanyId());
            if(i < customers.size()) {
                customersAndCompaniesDTO.setCustomerId(customers.get(i));
            }
            if(i < projects.size()) {
                customersAndCompaniesDTO.setCompanyId(projects.get(i));
            }
            customersAndCompaniesDTOList.add(customersAndCompaniesDTO);
        }
        return customersAndCompaniesDTOList;
    }
}
