package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.DevelopersOnProjectsDAO;
import ua.goit.service.developers.DevelopersOnProjectsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevelopersOnProjectsRepository implements MultiEntityRepository<DevelopersOnProjectsDAO> {

    private final HikariDataSource connectionManager;

    private static final String INSERT = "INSERT INTO developers_on_projects (developer_id, project_id)" +
            "VALUES (?, ?);";

    private static final String UPDATE = "UPDATE developers_on_projects SET developer_id=?, project_id=?" +
            "WHERE developer_id=? and project_id=?;";

    private static final String DELETE_FOR_DEVELOPERS = "DELETE FROM developers_on_projects WHERE developer_id=?;";

    private static final String DELETE_FOR_PROJECTS = "DELETE FROM developers_on_projects WHERE project_id=?;";

    private static final String DELETE_UNIQUE_VALUE = "DELETE FROM developers_on_projects WHERE developer_id=? and project_id=?;";

    private static final String SELECT_DEVELOPER_ON_PROJECT = "SELECT developer_id, project_id " +
            "FROM developers_on_projects " +
            "WHERE developer_id=? and project_id=?;";

    private static final String SELECT_BY_PROJECT = "SELECT developer_id, project_id " +
            "FROM developers_on_projects " +
            "WHERE project_id=?;";

    private static final String SELECT_BY_DEVELOPER = "SELECT developer_id, project_id " +
            "FROM developers_on_projects " +
            "WHERE developer_id=?;";

    public DevelopersOnProjectsRepository(HikariDataSource connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void create(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatement(developersOnProjectsDAO, INSERT);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatement(developersOnProjectsDAO, UPDATE);
            preparedStatement.setLong(1, developersOnProjectsDAO.getDeveloperId());
            preparedStatement.setLong(2, developersOnProjectsDAO.getProjectId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DevelopersOnProjectsDAO findUniqueValue(long developerId, long projectId) {
        ResultSet resultSet;
        DevelopersOnProjectsDAO developersOnProjectsDAO = new DevelopersOnProjectsDAO();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPER_ON_PROJECT)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.setLong(2, projectId);
            resultSet = preparedStatement.executeQuery();
            developersOnProjectsDAO = DevelopersOnProjectsConverter.toDeveloperOnProject(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return developersOnProjectsDAO;
    }

    public List<DevelopersOnProjectsDAO> findByProject(long projectId) {
        return findByEntity(projectId, SELECT_BY_PROJECT);
    }

    public List<DevelopersOnProjectsDAO> findByDeveloper(long developerId) {
        return findByEntity(developerId, SELECT_BY_DEVELOPER);
    }

    public void deleteUniqueRecord(long developerId, long projectId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_UNIQUE_VALUE)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.setLong(2, projectId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteForDevelopers(long developerId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOR_DEVELOPERS)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteForProjects(long projectId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOR_PROJECTS)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement prepareStatement(DevelopersOnProjectsDAO developersOnProjectsDAO, String statement) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setLong(1, developersOnProjectsDAO.getDeveloperId());
        preparedStatement.setLong(2, developersOnProjectsDAO.getProjectId());
        return preparedStatement;
    }

    private List<DevelopersOnProjectsDAO> findByEntity(long entityId, String statement) {
        ResultSet resultSet;
        List<DevelopersOnProjectsDAO> developersOnProjectsDAOList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setLong(1, entityId);
            resultSet = preparedStatement.executeQuery();
            developersOnProjectsDAOList = DevelopersOnProjectsConverter.toDeveloperOnProjectCollection(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return developersOnProjectsDAOList;
    }
}
