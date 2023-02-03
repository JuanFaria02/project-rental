package model.dao;

import model.entities.Type;


import java.util.List;

public interface TypeDao {
    Type insert(Type obj);
    Type update(Type obj);
    void deleteById(Integer id);
    Type findById(Integer id);
    List<Type> findAll();
    Type findByName(String name);

}
