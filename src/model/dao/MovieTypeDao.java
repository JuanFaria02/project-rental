package model.dao;


import model.entities.MovieType;

import java.util.List;

public interface MovieTypeDao {
    void insert(MovieType obj);
    void update(MovieType obj);
    void deleteById(Integer id);
    MovieType findById(Integer id);
    List<MovieType> findAll();
}
