package application;

import db.DB;
import model.dao.*;
import model.dao.impl.MediaDaoJdbc;
import model.entities.*;

import java.net.DatagramPacket;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Date date = Date.valueOf(LocalDate.now());

        RentalDao rentalDao = DaoFactory.createRentalDao();

        List<Rental> rentals = rentalDao.findAll();
        rentals.forEach(System.out::println);
    }
}