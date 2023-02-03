package model.dao;


import model.entities.Movie;

import java.util.List;
import java.util.MissingFormatArgumentException;

public interface MovieDao {
    Movie insert(Movie obj);
    Movie update(Movie obj);
    void deleteById(Integer id);
    Movie findById(Integer id);
    List<Movie> findAll();
    List<Movie> findByDirector(String name);
}
