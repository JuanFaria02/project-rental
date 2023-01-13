package application;

import db.DB;
import model.dao.*;
import model.entities.*;

import java.net.DatagramPacket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ClientDao clientDao = DaoFactory.createClientDao();
        Client client = new Client("123023012", "José");
        clientDao.insert(client);

    }
}