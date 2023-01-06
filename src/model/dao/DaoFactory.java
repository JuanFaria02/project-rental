package model.dao;

import db.DB;
import model.dao.impl.ClientDaoJdbc;
import model.dao.impl.MovieDaoJdbc;
import model.dao.impl.MovieTypeDaoJdbc;
import model.dao.impl.TypeDaoJdbc;
import model.entities.MovieType;

import java.sql.Connection;

public class DaoFactory {


    public static ClientDaoJdbc createClientDao() {
        return new ClientDaoJdbc(DB.getConnection());
    }

    public static MovieDaoJdbc createMovieDao() {
        return new MovieDaoJdbc(DB.getConnection());
    }

    public static TypeDaoJdbc createTypeDao() {
        return new TypeDaoJdbc(DB.getConnection());
    }

    public static MovieTypeDaoJdbc createMovieTypeDao(){
        return new MovieTypeDaoJdbc(DB.getConnection());
    }
}
