package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dto.DevelopersDTO;
import ua.goit.service.developers.DevelopersConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DevelopersRepository implements Repository<DevelopersDAO> {
    private final HikariDataSource dataSource;

    private static final String INSERT = "INSERT INTO developers (developer_id, first_name, last_name, " +
            "gender, age, experience_in_years, company_id, salary, developer_email)" +
            "VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_DEVELOPERS_BY_ID = "SELECT developer_id, first_name, last_name, " +
            "gender, age, experience_in_years, company_id, salary, developer_email " +
            "FROM developers WHERE developer_id = ?;";

    private static final String SELECT_ALL_DEVELOPERS = "SELECT developer_id, first_name, last_name, " +
            "gender, age, experience_in_years, company_id, salary, developer_email " +
            "FROM developers;";

    private static final String UPDATE = "UPDATE developers SET first_name=?, last_name=?, " +
            "gender=?, age=?, experience_in_years=?, company_id=?, salary=?, developer_email=?" +
            "WHERE developer_email=?;";

    private static final String DELETE_BY_EMAIL = "DELETE FROM developers WHERE developer_email=?;";

    private static final String DELETE_BY_ID = "DELETE FROM developers WHERE developer_id=?;";

    private static final String SELECT_DEVELOPER_BY_EMAIL = "SELECT developer_id, first_name, last_name, " +
            "gender, age, experience_in_years, company_id, salary, developer_email " +
            "FROM developers WHERE developer_email=?";

    private static final String SELECT_SUM_SALARY_ON_PROJECT = "SELECT SUM(salary) FROM developers d " +
            "INNER JOIN developers_on_projects as dp on d.developer_id=dp.developer_id " +
            "WHERE project_id=?;";

    private static final String SELECT_DEVELOPERS_ON_PROJECT = "SELECT d.developer_id, first_name, last_name, gender, " +
            "age, experience_in_years, company_id, salary, developer_email FROM developers as d " +
            "INNER JOIN developers_on_projects as dp on d.developer_id=dp.developer_id " +
            "WHERE project_id=?;";

    private static final String SELECT_DEVELOPERS_BY_STACK = "SELECT developer_id, first_name, last_name, gender, " +
            "age, experience_in_years, company_id, salary, d.developer_email " +
            "FROM developers d INNER JOIN skills as s on d.developer_email=s.developer_email " +
            "WHERE stack::text ILIKE ?;";

    private static final String SELECT_DEVELOPERS_BY_LEVEL = "SELECT developer_id, first_name, last_name, gender, " +
            "age, experience_in_years, company_id, salary, d.developer_email " +
            "FROM developers d INNER JOIN skills as s on d.developer_email=s.developer_email " +
            "WHERE level::text ILIKE ?;";

    public DevelopersRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DevelopersDAO findById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDeveloper(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new DevelopersDAO();
    }

    @Override
    public void create(DevelopersDAO developersDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatment(developersDAO, INSERT);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DevelopersDAO developersDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatment(developersDAO, UPDATE);
            preparedStatement.setString(1, developersDAO.getDeveloperEmail());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_EMAIL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DevelopersDAO findByUniqueValue(String email) {
        ResultSet resultSet;
        DevelopersDAO developersDAO = new DevelopersDAO();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            developersDAO = DevelopersConverter.toDeveloper(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return developersDAO;
    }

    public List<DevelopersDAO> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEVELOPERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDevelopersCollection(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int countSumSalary(long projectId) {
        ResultSet resultSet;
        int sum = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUM_SALARY_ON_PROJECT)) {
            preparedStatement.setLong(1, projectId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sum;
    }

    public List<DevelopersDTO> selectDevelopersOnProject(long projectId) {
        ResultSet resultSet;
        List<DevelopersDAO> listDAO;
        List<DevelopersDTO> listOfDevelopers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_ON_PROJECT)) {
            preparedStatement.setLong(1, projectId);
            resultSet = preparedStatement.executeQuery();
            listDAO = DevelopersConverter.toDevelopersList(resultSet);
            listOfDevelopers = listDAO.stream()
                    .map(DevelopersConverter::fromDeveloper)
                    .collect(Collectors.toList());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOfDevelopers;
    }

    public List<DevelopersDTO> selectDevelopersByStack(String stack) {
        return selectDevelopersByParametr(SELECT_DEVELOPERS_BY_STACK, stack);
    }

    public List<DevelopersDTO> selectDevelopersByLevel(String level) {
        return selectDevelopersByParametr(SELECT_DEVELOPERS_BY_LEVEL, level);
    }

    public PreparedStatement prepareStatment(DevelopersDAO developersDAO, String statement) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, developersDAO.getFirstName());
        preparedStatement.setString(2, developersDAO.getLastName());
        preparedStatement.setString(3, developersDAO.getGender());
        preparedStatement.setInt(4, developersDAO.getAge());
        preparedStatement.setInt(5, developersDAO.getExperienceInYears());
        preparedStatement.setInt(6, developersDAO.getCompanyId());
        preparedStatement.setInt(7, developersDAO.getSalary());
        preparedStatement.setString(8, developersDAO.getDeveloperEmail());
        return preparedStatement;
    }

    public List<DevelopersDTO> selectDevelopersByParametr(String query, String value) {
        ResultSet resultSet;
        List<DevelopersDAO> listDAO;
        List<DevelopersDTO> listOfDevelopers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();
            listDAO = DevelopersConverter.toDevelopersList(resultSet);
            listOfDevelopers = listDAO.stream()
                    .map(DevelopersConverter::fromDeveloper)
                    .collect(Collectors.toList());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOfDevelopers;
    }
}
