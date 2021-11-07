package ua.goit.service.developers;

import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dto.DevelopersDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevelopersConverter {
    public static DevelopersDAO toDevelopersCollection(DevelopersDTO developersDTO) {
        return new DevelopersDAO(developersDTO.getDeveloperId(), developersDTO.getFirstName(),
                developersDTO.getLastName(), developersDTO.getGender(), developersDTO.getAge(),
                developersDTO.getExperienceInYears(), developersDTO.getCompanyId(), developersDTO.getSalary(),
                developersDTO.getDeveloperEmail());
    }

    public static DevelopersDTO fromDeveloper(DevelopersDAO developersDAO) {
        return new DevelopersDTO(developersDAO.getDeveloperId(), developersDAO.getFirstName(),
                developersDAO.getLastName(), developersDAO.getGender(), developersDAO.getAge(),
                developersDAO.getExperienceInYears(), developersDAO.getCompanyId(), developersDAO.getSalary(),
                developersDAO.getDeveloperEmail());
    }

    public static List<DevelopersDAO> toDevelopersCollection(ResultSet resultSet) throws SQLException {
        List<DevelopersDAO> developersDAOList = new ArrayList<>();
        while (resultSet.next()) {
            DevelopersDAO developersDAO = new DevelopersDAO();
            developersDAO.setDeveloperId(resultSet.getLong("developer_id"));
            developersDAO.setFirstName(resultSet.getString("first_name"));
            developersDAO.setLastName(resultSet.getString("last_name"));
            developersDAO.setGender(resultSet.getString("gender"));
            developersDAO.setAge(resultSet.getInt("age"));
            developersDAO.setExperienceInYears(resultSet.getInt("experience_in_years"));
            developersDAO.setCompanyId(resultSet.getInt("company_id"));
            developersDAO.setSalary(resultSet.getInt("salary"));
            developersDAO.setDeveloperEmail(resultSet.getString("developer_email"));
            developersDAOList.add(developersDAO);
        }
        return developersDAOList;
    }

    public static DevelopersDAO toDeveloper(ResultSet resultSet) throws SQLException {
        DevelopersDAO developersDAO = new DevelopersDAO();
        while (resultSet.next()) {
            developersDAO.setDeveloperId(resultSet.getLong("developer_id"));
            developersDAO.setFirstName(resultSet.getString("first_name"));
            developersDAO.setLastName(resultSet.getString("last_name"));
            developersDAO.setGender(resultSet.getString("gender"));
            developersDAO.setAge(resultSet.getInt("age"));
            developersDAO.setExperienceInYears(resultSet.getInt("experience_in_years"));
            developersDAO.setCompanyId(resultSet.getInt("company_id"));
            developersDAO.setSalary(resultSet.getInt("salary"));
            developersDAO.setDeveloperEmail(resultSet.getString("developer_email"));
        }
        return developersDAO;
    }

    public static List<DevelopersDAO> toDevelopersList(ResultSet resultSet) throws SQLException {
        List<DevelopersDAO> developersList = new ArrayList<>();
        while (resultSet.next()) {
            DevelopersDAO developersDAO = toDeveloper(resultSet);
            developersList.add(developersDAO);
        }
        return developersList;
    }
}
