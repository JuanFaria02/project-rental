package model.dao;

import db.DB;
import model.dao.impl.*;




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
    public static MediaDaoJdbc createMediaDao(){
        return new MediaDaoJdbc(DB.getConnection());
    }

    public static RentalDaoJdbc createRentalDao(){
        return new RentalDaoJdbc(DB.getConnection());
    }
}
