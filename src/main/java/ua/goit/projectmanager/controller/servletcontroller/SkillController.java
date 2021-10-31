package ua.goit.projectmanager.controller.servletcontroller;

import ua.goit.projectmanager.model.Skill;

public class SkillController extends Controller<Skill, Long>{
    protected SkillController(Class<Skill> modelClass) {
        super(Skill.class);
    }
}
