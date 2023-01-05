package application;

import db.DB;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        ClientDao clientDao = DaoFactory.createClientDao();

        Client client = new Client("4333231111", "Marcos");
        clientDao.insert(client);



    }
}