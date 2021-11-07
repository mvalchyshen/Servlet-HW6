package ua.goit.service.customers;

import ua.goit.dao.model.CustomersAndCompaniesDAO;
import ua.goit.dto.CustomersAndCompaniesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersAndCompaniesConverter {
    public static CustomersAndCompaniesDAO toCustomerAndCompaniesCollection(CustomersAndCompaniesDTO customersAndCompaniesDTO) {
        return new CustomersAndCompaniesDAO(
                customersAndCompaniesDTO.getCompanyId(),
                customersAndCompaniesDTO.getCustomerId(),
                customersAndCompaniesDTO.getProjectId()
        );
    }

    public static CustomersAndCompaniesDTO fromCustomerAndCompanies(CustomersAndCompaniesDAO customersAndCompaniesDAO) {
        return new CustomersAndCompaniesDTO(
                customersAndCompaniesDAO.getCompanyId(),
                customersAndCompaniesDAO.getCustomerId(),
                customersAndCompaniesDAO.getProjectId()
        );
    }

    public static List<CustomersAndCompaniesDAO> toCustomerAndCompaniesCollection(ResultSet resultSet) throws SQLException {
        List<CustomersAndCompaniesDAO> customersAndCompaniesDAOList = new ArrayList<>();
        while (resultSet.next()) {
            CustomersAndCompaniesDAO customersAndCompaniesDAO = new CustomersAndCompaniesDAO();
            customersAndCompaniesDAO.setCompanyId(resultSet.getLong("company_id"));
            customersAndCompaniesDAO.setCustomerId(resultSet.getLong("customer_id"));
            customersAndCompaniesDAO.setProjectId(resultSet.getLong("project_id"));
            customersAndCompaniesDAOList.add(customersAndCompaniesDAO);
        }
        return customersAndCompaniesDAOList;
    }

    public static CustomersAndCompaniesDAO toCustomerAndCompanies(ResultSet resultSet) throws SQLException {
        CustomersAndCompaniesDAO customersAndCompaniesDAO = new CustomersAndCompaniesDAO();
        while (resultSet.next()) {
            customersAndCompaniesDAO.setCompanyId(resultSet.getLong("company_id"));
            customersAndCompaniesDAO.setCustomerId(resultSet.getLong("customer_id"));
            customersAndCompaniesDAO.setProjectId(resultSet.getLong("project_id"));
        }
        return customersAndCompaniesDAO;
    }
}
