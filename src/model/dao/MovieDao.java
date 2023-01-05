package model.dao;


import model.entities.Movie;

import java.util.List;

public interface MovieDao {
    void insert(Movie obj);
    void update(Movie obj);
    void deleteById(Integer id);
    Movie findById(Integer id);
    List<Movie> findAll();
}
