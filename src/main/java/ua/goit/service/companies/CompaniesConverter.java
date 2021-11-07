package ua.goit.service.companies;

import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dto.CompaniesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompaniesConverter {
    public static CompaniesDAO toCompaniesCollection(CompaniesDTO companiesDTO) {
        return new CompaniesDAO(companiesDTO.getCompanyId(), companiesDTO.getCompanyName(),
                companiesDTO.getNumberOfDevelopers());
    }

    public static CompaniesDTO fromCompany(CompaniesDAO companiesDAO) {
        return new CompaniesDTO(companiesDAO.getCompanyId(), companiesDAO.getCompanyName(),
                companiesDAO.getNumberOfDevelopers());
    }

    public static List<CompaniesDAO> toCompaniesCollection(ResultSet resultSet) throws SQLException {
        List<CompaniesDAO> companiesDAOList = new ArrayList<>();
        while (resultSet.next()) {
            CompaniesDAO companiesDAO = new CompaniesDAO();
            companiesDAO.setCompanyId(resultSet.getLong("company_id"));
            companiesDAO.setCompanyName(resultSet.getString("company_name"));
            companiesDAO.setNumberOfDevelopers(resultSet.getInt("number_of_developers"));
            companiesDAOList.add(companiesDAO);
        }
        return companiesDAOList;
    }

    public static CompaniesDAO toCompany(ResultSet resultSet) throws SQLException {
        CompaniesDAO companiesDAO = new CompaniesDAO();
        while (resultSet.next()) {
            companiesDAO.setCompanyId(resultSet.getLong("company_id"));
            companiesDAO.setCompanyName(resultSet.getString("company_name"));
            companiesDAO.setNumberOfDevelopers(resultSet.getInt("number_of_developers"));
        }
        return companiesDAO;
    }
}
