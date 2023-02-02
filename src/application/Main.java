package application;


import db.DbException;
import model.entities.Client;
import resources.ClientResources;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        ClientResources clientResources = new ClientResources();
        try {


            System.out.println(clientResources.findByCpf("1313411345"));
        }
        catch (DbException e) {
            System.out.println(e.getMessage());
        }
    }
}