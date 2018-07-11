package app.repostiories.base;

import java.util.List;

public interface GenericRepository<T> {
    List<T> getAll();

    T getById(int id);

    T create(T entity);

    void setEntityClass(Class<T> hotelClass);

    T delete(T entity);

    void deleteById(int id);
}
