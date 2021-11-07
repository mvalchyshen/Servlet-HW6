package ua.goit.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        req.setAttribute("message", message);
        req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
    }
}
