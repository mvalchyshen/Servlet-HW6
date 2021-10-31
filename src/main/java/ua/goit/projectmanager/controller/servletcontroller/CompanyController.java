package ua.goit.projectmanager.controller.servletcontroller;

import ua.goit.projectmanager.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/company/")
public class CompanyController extends Controller<Company, Long> {
    protected CompanyController() {
        super(Company.class);
    }
}
