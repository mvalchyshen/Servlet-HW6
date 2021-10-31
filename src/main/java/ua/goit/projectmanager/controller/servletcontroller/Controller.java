package ua.goit.projectmanager.controller.servletcontroller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.SneakyThrows;
import ua.goit.projectmanager.model.BaseEntity;
import ua.goit.projectmanager.repository.BaseRepository;
import ua.goit.projectmanager.repository.RepositoryFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Controller<E extends BaseEntity<ID>, ID> extends HttpServlet {

    private Class<E> modelClass;

    protected Controller(Class<E> modelClass){
        this.modelClass = modelClass;
    }

    private final BaseRepository<E, ID> repository = RepositoryFactory.of(modelClass);
    private final Gson gson = new Gson();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getId(req, resp).ifPresent(id -> repository.deleteById(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String payload = req.getReader().lines().collect(Collectors.joining("\n"));
            E e = gson.fromJson(payload, modelClass);
            sendAsJson(resp, repository.save(e));
        } catch (JsonSyntaxException e) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo()==null || req.getPathInfo().equals("/")) sendAsJson(resp, repository.getAll());
        else getId(req, resp).ifPresent(id -> {
            repository.getById(id)
                    .map(company -> sendAsJson(resp, company))
                    .orElseGet(() -> sendError(resp, HttpServletResponse.SC_NOT_FOUND));
        });
    }

    @SneakyThrows
    private String sendAsJson(HttpServletResponse resp, Object payload) {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String result = gson.toJson(payload);
        writer.print(result);
        writer.flush();
        return result;
    }

    @SneakyThrows
    private String sendError(HttpServletResponse resp, int httpStatus){
        resp.sendError(httpStatus);
        return "Error response with status : "+httpStatus;
    }

    private Optional<ID> getId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            final String[] split = req.getPathInfo().split("/");
            if (split.length != 2) sendError(resp, HttpServletResponse.SC_BAD_REQUEST);
            return Optional.of((ID)split[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST);
            return Optional.empty();
        }
    }
}
