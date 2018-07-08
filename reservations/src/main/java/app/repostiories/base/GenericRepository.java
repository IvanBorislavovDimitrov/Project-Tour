package app.repostiories.base;

import app.entities.base.Entity;

import java.util.List;

public interface GenericRepository<T extends Entity> {
    List<T> getAll();

    T getById(int id);

    T create(T entity);

    void setEntityClass(Class<T> hotelClass);
}
