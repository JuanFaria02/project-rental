package application;

import db.DB;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ClientDao clientDao = DaoFactory.createClientDao();

        List<Client> clients = clientDao.findAll();
        clients.forEach(System.out::println);
    }
}