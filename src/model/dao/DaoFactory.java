package model.dao;

import db.DB;
import model.dao.impl.ClientDaoJdbc;

import java.sql.Connection;

public class DaoFactory {


    public static ClientDaoJdbc createClientDao() {
        return new ClientDaoJdbc(DB.getConnection());
    }

}
