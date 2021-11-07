package ua.goit.service.skills;

import ua.goit.dao.model.Levels;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dao.model.Stack;
import ua.goit.dto.SkillsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillsConverter {
    public static SkillsDAO toSkill(SkillsDTO skillsDTO) {
        return new SkillsDAO(skillsDTO.getRecordId(), skillsDTO.getStack(),
                skillsDTO.getLevel(), skillsDTO.getDeveloperEmail());
    }

    public static SkillsDTO fromSkill(SkillsDAO skillsDAO) {
        return new SkillsDTO(skillsDAO.getRecordId(), skillsDAO.getSatck(),
                skillsDAO.getLevel(), skillsDAO.getDeveloperEmail());
    }

    public static SkillsDAO toSkill(ResultSet resultSet) throws SQLException {
        SkillsDAO skillsDAO = new SkillsDAO();
        while (resultSet.next()) {
            skillsDAO.setRecordId(resultSet.getInt("record_id"));
            skillsDAO.setSatck(Stack.valueOf(resultSet.getString("stack")));
            skillsDAO.setLevel(Levels.valueOf(resultSet.getString("level")));
            skillsDAO.setDeveloperEmail(resultSet.getString("developer_email"));
        }
        return skillsDAO;
    }
}
