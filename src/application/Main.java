package application;

import db.DB;
import model.dao.*;
import model.entities.Client;
import model.entities.Movie;
import model.entities.MovieType;
import model.entities.Type;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieTypeDao movieTypeDao = DaoFactory.createMovieTypeDao();
        MovieDao movieDao = DaoFactory.createMovieDao();
        TypeDao typeDao = DaoFactory.createTypeDao();

 
        List<MovieType> movieTypes = movieTypeDao.findAll();

        movieTypes.forEach(System.out::println);
    }
}