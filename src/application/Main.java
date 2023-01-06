package application;

import db.DB;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.dao.TypeDao;
import model.entities.Client;
import model.entities.Movie;
import model.entities.Type;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TypeDao typeDao = DaoFactory.createTypeDao();
        List<Type> type = typeDao.findAll();
        type.forEach(System.out::println);
    }
}