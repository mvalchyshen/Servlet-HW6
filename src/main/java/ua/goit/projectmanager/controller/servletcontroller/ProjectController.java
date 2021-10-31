package ua.goit.projectmanager.controller.servletcontroller;

import ua.goit.projectmanager.model.Project;

public class ProjectController extends Controller<Project, Long> {
    protected ProjectController() {
        super(Project.class);
    }
}
