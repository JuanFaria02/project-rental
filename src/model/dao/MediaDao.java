package model.dao;

import model.entities.Media;

import java.util.List;

public interface MediaDao {
    Media insert(Media obj);
    Media update(Media obj);
    void deleteById(Integer id);
    Media findById(Integer id);
    List<Media> findAll();
}
