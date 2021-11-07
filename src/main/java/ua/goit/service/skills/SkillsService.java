package ua.goit.service.skills;

import ua.goit.dao.Repository;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dto.SkillsDTO;

public class SkillsService {
    private Repository<SkillsDAO> repository;

    public SkillsService(Repository<SkillsDAO> repository) {
        this.repository = repository;
    }

    public SkillsDTO create(SkillsDTO skillsDTO) {
        SkillsDAO skillsDAO = SkillsConverter.toSkill(skillsDTO);
        repository.create(skillsDAO);
        SkillsDAO savedSkillsDAO = repository.findByUniqueValue(skillsDAO.getDeveloperEmail());
        return SkillsConverter.fromSkill(savedSkillsDAO);
    }

    public SkillsDTO update(SkillsDTO skillsDTO) {
        SkillsDAO skillsDAO = SkillsConverter.toSkill(skillsDTO);
        repository.update(skillsDAO);
        SkillsDAO updatedSkillsDAO = repository.findByUniqueValue(skillsDTO.getDeveloperEmail());
        return SkillsConverter.fromSkill(updatedSkillsDAO);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public SkillsDTO findByDeveloper(String email) {
        return SkillsConverter.fromSkill(repository.findByUniqueValue(email));
    }
}
