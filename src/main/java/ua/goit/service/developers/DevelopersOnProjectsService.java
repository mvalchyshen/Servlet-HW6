package ua.goit.service.developers;

import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.model.DevelopersOnProjectsDAO;
import ua.goit.dto.DevelopersOnProjectsDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DevelopersOnProjectsService {
    private DevelopersOnProjectsRepository repository;

    public DevelopersOnProjectsService(DevelopersOnProjectsRepository repository) {
        this.repository = repository;
    }

    public DevelopersOnProjectsDTO create(DevelopersOnProjectsDTO developersOnProjectsDTO) {
        DevelopersOnProjectsDAO developersOnProjectsDAO = DevelopersOnProjectsConverter.toDevelopersOnProjects(developersOnProjectsDTO);
        repository.create(developersOnProjectsDAO);
        DevelopersOnProjectsDAO savedDevelopersOnProjectsDAO = repository.findUniqueValue(
                developersOnProjectsDTO.getDeveloperId(),
                developersOnProjectsDTO.getProjectId()
        );

        return DevelopersOnProjectsConverter.fromDevelopersOnProjects(savedDevelopersOnProjectsDAO);
    }

    public DevelopersOnProjectsDTO update(DevelopersOnProjectsDTO developersOnProjectsDTO) {
        DevelopersOnProjectsDAO developersOnProjectsDAO = DevelopersOnProjectsConverter.toDevelopersOnProjects(developersOnProjectsDTO);
        repository.update(developersOnProjectsDAO);
        DevelopersOnProjectsDAO updatedDevelopersOnProjectsDAO = repository.findUniqueValue(
                developersOnProjectsDTO.getDeveloperId(),
                developersOnProjectsDTO.getProjectId()
        );
        return DevelopersOnProjectsConverter.fromDevelopersOnProjects(updatedDevelopersOnProjectsDAO);
    }

    public void delete(long developerId, long projectId) {
        repository.deleteUniqueRecord(developerId, projectId);
    }

    public List<DevelopersOnProjectsDTO> findByProjectId(long projectId) {
        List<DevelopersOnProjectsDAO> DevelopersOnProjectsDAOList = repository.findByProject(projectId);
        return DevelopersOnProjectsDAOList.stream()
                .map(DevelopersOnProjectsConverter::fromDevelopersOnProjects)
                .collect(Collectors.toList());
    }
}
