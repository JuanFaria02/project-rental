package model.dao;


import model.entities.MovieType;

import java.util.List;

public interface MovieTypeDao {
    MovieType insert(MovieType obj);
    MovieType update(MovieType obj);
    void deleteByIdMovie(Integer id);

    MovieType findById(Integer id);

    List<MovieType> findAll();
    List<MovieType> findByName(String name);

}
