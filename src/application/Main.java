package application;

import db.DB;
import model.dao.*;
import model.entities.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieDao movieDao = DaoFactory.createMovieDao();
        MediaDao mediaDao = DaoFactory.createMediaDao();
        Media media = new Media("78643", movieDao.findById(1));
        media.setId(1);
        mediaDao.update(media);
    }
}