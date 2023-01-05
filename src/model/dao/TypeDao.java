package model.dao;

import model.entities.Movie;
import model.entities.Type;


import java.util.List;

public interface TypeDao {
    void insert(Type obj);
    void update(Type obj);
    void deleteById(Integer id);
    Type findById(Integer id);
    List<Type> findAll();
}
