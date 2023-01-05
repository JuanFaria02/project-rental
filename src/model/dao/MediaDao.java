package model.dao;

import model.entities.Media;
import model.entities.MovieType;

import java.util.List;

public interface MediaDao {
    void insert(Media obj);
    void update(Media obj);
    void deleteById(Integer id);
    Media findById(Integer id);
    List<Media> findAll();
}
