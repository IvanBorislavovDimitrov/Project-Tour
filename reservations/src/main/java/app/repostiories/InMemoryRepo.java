package app.repostiories;

import app.entities.base.ModelEntity;
import app.repostiories.base.GenericRepository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryRepo<T extends ModelEntity> implements GenericRepository<T> {
     private List<T> entities;


    public InMemoryRepo() {
        this.entities = new ArrayList<>();
    }

    @Override
    public List<T> getAll() {
        return entities;
    }

    @Override
    public T getById(int id) {
        T result = null;

        for (T entity : entities) {
            if(entity.getId()==id){
                return entity;
            }
        }
        return null;
    }

    @Override
    public T create(T entity) {
        int id = -1;
        if(entities.size()==0){
            id= 1;
        }else {
            id = entities.get(entities.size() - 1).getId() + 1;
            this.entities.add(entity);
            return entity;
        }
        entity.setId(id);
        this.entities.add(entity);
        return entity;
    }

    @Override
    public void setEntityClass(Class<T> hotelClass) {

    }
}
