package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.CompaniesDAO;
import ua.goit.service.companies.CompaniesConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompaniesRepository implements Repository<CompaniesDAO> {
    private final HikariDataSource dataSource;

    private static final String INSERT = "INSERT INTO companies (company_id, company_name, number_of_developers) " +
            "VALUES (default, ?, ?);";
    private static final String SELECT_COMPANY_BY_ID = "SELECT company_id, company_name, number_of_developers " +
            "FROM companies WHERE company_id = ?;";
    private static final String SELECT_ALL_COMPANIES = "SELECT company_id, company_name, number_of_developers " +
            "FROM companies;";
    private static final String UPDATE = "UPDATE companies SET company_name=?, number_of_developers=?" +
            "WHERE company_id=?;";
    private static final String DELETE = "DELETE FROM companies WHERE company_id=?;";

    private static final String SELECT_COMPANY_BY_NAME = "SELECT company_id, company_name, number_of_developers " +
            "FROM companies WHERE company_name = ?;";

    public CompaniesRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CompaniesDAO findById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CompaniesConverter.toCompany(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new CompaniesDAO();
    }

    @Override
    public CompaniesDAO findByUniqueValue(String companyName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_NAME)) {
            preparedStatement.setString(1, companyName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CompaniesConverter.toCompany(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new CompaniesDAO();
    }

    @Override
    public void create(CompaniesDAO companiesDAO) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, companiesDAO.getCompanyName());
            preparedStatement.setInt(2, companiesDAO.getNumberOfDevelopers());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(CompaniesDAO companiesDAO) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, companiesDAO.getCompanyName());
            preparedStatement.setInt(2, companiesDAO.getNumberOfDevelopers());
            preparedStatement.setLong(3, companiesDAO.getCompanyId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<CompaniesDAO> findAllCompanies() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPANIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return CompaniesConverter.toCompaniesCollection(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
