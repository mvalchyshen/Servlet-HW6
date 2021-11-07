package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.service.projects.ProjectsConverter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectsRepository implements Repository<ProjectsDAO> {
    private final HikariDataSource dataSource;

    private static final String INSERT = "INSERT INTO projects (project_id, project_name, stage, time_period, coast, " +
            "number_of_developers, date_of_beginning) " +
            "VALUES (default, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PROJECTS_BY_ID = "SELECT project_id, project_name, stage, time_period, coast, " +
            "number_of_developers, date_of_beginning) " +
            "FROM projects WHERE project_id=?;";
    private static final String UPDATE = "UPDATE projects SET project_name=?, stage=?, time_period=?, coast=?, " +
            "number_of_developers=?, date_of_beginning=? " +
            "WHERE project_id=?;";
    private static final String DELETE = "DELETE FROM projects WHERE project_name=?;";
    private static final String SELECT_PROJECT_BY_NAME = "SELECT project_id, project_name, stage, time_period, coast, " +
            "number_of_developers, date_of_beginning " +
            "FROM projects WHERE project_name=?;";
    private static final String SELECT_ALL_PROJECTS = "SELECT project_id, project_name, stage, time_period, coast, " +
            "number_of_developers, date_of_beginning " +
            "FROM projects";

    public ProjectsRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public ProjectsDAO findById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECTS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ProjectsConverter.toProject(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new ProjectsDAO();
    }

    @Override
    public ProjectsDAO findByUniqueValue(String projectName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_BY_NAME)) {
            preparedStatement.setString(1, projectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ProjectsConverter.toProject(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new ProjectsDAO();
    }

    @Override
    public void create(ProjectsDAO projectsDAO) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, projectsDAO.getProjectName());
            preparedStatement.setString(2, projectsDAO.getStage());
            preparedStatement.setInt(3, projectsDAO.getTimePeriod());
            preparedStatement.setInt(4, projectsDAO.getCoast());
            preparedStatement.setInt(5, projectsDAO.getNumberOfDevelopers());
            preparedStatement.setDate(6, Date.valueOf((LocalDate) projectsDAO.getDateOfBeginning()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ProjectsDAO projectsDAO) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, projectsDAO.getProjectName());
            preparedStatement.setString(2, projectsDAO.getStage());
            preparedStatement.setInt(3, projectsDAO.getTimePeriod());
            preparedStatement.setInt(4, projectsDAO.getCoast());
            preparedStatement.setLong(5, projectsDAO.getProjectId());
            preparedStatement.setInt(5, projectsDAO.getNumberOfDevelopers());
            preparedStatement.setDate(6, Date.valueOf((LocalDate) projectsDAO.getDateOfBeginning()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<ProjectsDAO> findAllProjects() {
        List<ProjectsDAO> projectsDAOList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            projectsDAOList = ProjectsConverter.toProjectsList(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projectsDAOList;
    }
}
