package ua.goit.projectmanager.controller.servletcontroller;

import ua.goit.projectmanager.model.Developer;

public class DeveloperController extends Controller<Developer, Long> {
    protected DeveloperController() {
        super(Developer.class);
    }
}
