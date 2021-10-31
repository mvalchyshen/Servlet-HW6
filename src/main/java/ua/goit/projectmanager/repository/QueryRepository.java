package ua.goit.projectmanager.repository;

import ua.goit.projectmanager.model.Developer;
import ua.goit.projectmanager.model.Level;
import ua.goit.projectmanager.model.Project;
import ua.goit.projectmanager.model.Skill;
import ua.goit.projectmanager.model.dto.ProjectDevDto;

import java.io.Closeable;
import java.util.List;

public interface QueryRepository extends Closeable {
    Integer totalSalaryOfDevelopersOnProject(Long projectId);
    List<Developer> listOfDevelopersOnProject(Long projectId);
    List<Developer> listDevelopersByLanguage(String language);
    List<Developer> listOfDevelopersByLevel(Level level);
    List<ProjectDevDto> listOfProjects();
    static QueryRepository of(){
        return new QueryRepositoryImpl();
    }
}
