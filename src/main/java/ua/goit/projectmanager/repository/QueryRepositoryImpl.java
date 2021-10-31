package ua.goit.projectmanager.repository;

import lombok.SneakyThrows;
import ua.goit.projectmanager.model.Developer;
import ua.goit.projectmanager.model.Level;

import ua.goit.projectmanager.model.dto.ProjectDevDto;
import ua.goit.projectmanager.util.DataBaseConnection;
import ua.goit.projectmanager.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryRepositoryImpl implements QueryRepository {

    private final Connection connection;
    private final String schemeName = PropertiesLoader.getProperty("db.name");

    public QueryRepositoryImpl() {
        this.connection = DataBaseConnection.getInstance().getConnection();
    }

    @SneakyThrows
    @Override
    public Integer totalSalaryOfDevelopersOnProject(Long projectId) {
        Statement statement = connection.createStatement();
        String query = "SELECT SUM(developers.salary) AS sumSalaries FROM goit_hw_db.projects_developers \n" +
                "inner join goit_hw_db.developers on goit_hw_db.projects_developers.id_developer = developers.id \n" +
                "inner join goit_hw_db.projects on goit_hw_db.projects_developers.id_project = '" + projectId + "'" +
                "GROUP BY projects.id  limit 1;";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            return resultSet.getInt("sumSalaries");
        }
        return -1;
    }

    @SneakyThrows
    @Override
    public List<Developer> listOfDevelopersOnProject(Long projectId) {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM goit_hw_db.developers d, goit_hw_db.projects p, goit_hw_db.projects_developers dp" +
                " where  d.id=dp.id_developer and p.id = dp.id_project and p.id ='" + projectId + "'";
        ResultSet resultSet = statement.executeQuery(query);
        return getList(resultSet);
    }

    @SneakyThrows
    @Override
    public List<Developer> listDevelopersByLanguage(String language) {
        Statement statement = connection.createStatement();
        String query = "SELECT d.id, d.name_developer,d.age, d.salary FROM goit_hw_db.developers d " +
                "INNER JOIN goit_hw_db.developers_skills ds ON d.id = ds.id_developer " +
                "INNER JOIN goit_hw_db.skills s ON ds.id_skill = s.id" +
                " WHERE s.language ='" + language + "'";
        ResultSet resultSet = statement.executeQuery(query);
        return getList(resultSet);
    }

    @SneakyThrows
    @Override
    public List<Developer> listOfDevelopersByLevel(Level level) {
        Statement statement = connection.createStatement();
        String query = "SELECT d.id, d.name_developer,d.age, d.salary FROM goit_hw_db.developers d " +
                "INNER JOIN goit_hw_db.developers_skills ds ON d.id = ds.id_developer " +
                "INNER JOIN goit_hw_db.skills s ON ds.id_skill = s.id" +
                " WHERE s.level ='" + level + "'";
        ResultSet resultSet = statement.executeQuery(query);
        return getList(resultSet);
    }

    @SneakyThrows
    @Override
    public List<ProjectDevDto> listOfProjects() {
        Statement statement = connection.createStatement();
        String query = "SELECT p.id, p.create_date, p.name_project, count(d.name_developer) AS quantity FROM  projects p" +
                " JOIN  projects_developers dp ON  p.id = dp.id_project " +
                " JOIN  developers d on d.id = dp.id_developer " +
                "GROUP BY  p.name_project order by p.create_date";
        ResultSet resultSet = statement.executeQuery(query);
        List<ProjectDevDto> result = new ArrayList<>();
        while (resultSet.next()) {
            ProjectDevDto projectDevDto = ProjectDevDto.builder()
                    .projectDate(resultSet.getNString("create_date"))
                    .projectName(resultSet.getNString("name_project"))
                    .devCount(resultSet.getInt("quantity"))
                    .build();
            result.add(projectDevDto);
        }
        return result;
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }

    @SneakyThrows
    private List<Developer> getList(ResultSet resultSet) {
        List<Developer> list = new ArrayList<>();
        while (resultSet.next()) {
            Developer developer = Developer.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name_developer"))
                    .salary(resultSet.getInt("salary"))
                    .build();
            list.add(developer);
        }
        return list;
    }
}
