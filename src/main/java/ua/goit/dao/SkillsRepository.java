package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dao.model.Stack;
import ua.goit.service.skills.SkillsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillsRepository implements Repository<SkillsDAO> {
    private final HikariDataSource dataSource;

    private static final String INSERT = "INSERT INTO skills (record_id, stack, level, developer_email) " +
            "VALUES (default, ?, ?, ?);";
    private static final String SELECT_SKILLS_BY_RECORD_ID = "SELECT stack, level, developer_email " +
            "FROM skills WHERE record_id=?;";
    private static final String SELECT_SKILLS_BY_DEVELOPER_EMAIL = "SELECT stack, level, developer_email " +
            "FROM skills WHERE developer_email=?;";

    private static final String SELECT_SKILLS_FOR_DEVELOPER_BY_EMAIL = "SELECT stack, level, developer_email " +
            "FROM skills WHERE developer_email=? AND stack=?;";

    private static final String UPDATE = "UPDATE skills SET stack=?, level=?, developer_email=?) " +
            "WHERE record_id=?;";
    private static final String DELETE = "DELETE FROM skills WHERE record_id=?;";

    public SkillsRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public SkillsDAO findById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_SKILLS_BY_RECORD_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkill(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new SkillsDAO();
    }

    @Override
    public SkillsDAO findByUniqueValue(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_SKILLS_BY_DEVELOPER_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkill(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new SkillsDAO();
    }

    public SkillsDAO findSkillsForDeveloperByEmail(String email, Stack stack) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_SKILLS_FOR_DEVELOPER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(1, String.valueOf(stack));
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkill(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new SkillsDAO();
    }

    @Override
    public void create(SkillsDAO skillsDAO) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(INSERT)) {
            preparedStatement.setString(1, skillsDAO.getSatck().toString());
            preparedStatement.setString(2, skillsDAO.getLevel().toString());
            preparedStatement.setString(3, skillsDAO.getDeveloperEmail());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(SkillsDAO skillsDAO) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, skillsDAO.getSatck().toString());
            preparedStatement.setString(2, skillsDAO.getLevel().toString());
            preparedStatement.setString(3, skillsDAO.getDeveloperEmail());
            preparedStatement.setLong(4, skillsDAO.getRecordId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(DELETE)) {
            preparedStatement.setLong(1, Long.getLong(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
