package ua.goit.dao;

public interface MultiEntityRepository<T> {

    void create(T entity);

    void update(T entity);
}
