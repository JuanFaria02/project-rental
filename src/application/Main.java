package application;


import db.DbException;
import model.entities.Client;
import model.entities.Movie;
import resources.ClientResources;
import resources.MovieResources;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        MovieResources movieResources = new MovieResources();


        System.out.println(movieResources.findByDirector("Josh"));

    }
}