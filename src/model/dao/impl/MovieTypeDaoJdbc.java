package model.dao.impl;

import model.dao.MovieTypeDao;
import model.entities.MovieType;

import java.sql.Connection;
import java.util.List;

public class MovieTypeDaoJdbc implements MovieTypeDao {
    private Connection connection;
    public MovieTypeDaoJdbc(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(MovieType obj) {

    }

    @Override
    public void update(MovieType obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public MovieType findById(Integer id) {
        return null;
    }

    @Override
    public List<MovieType> findAll() {
        return null;
    }
}
