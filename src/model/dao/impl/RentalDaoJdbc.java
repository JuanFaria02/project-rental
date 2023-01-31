package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.MediaDao;
import model.dao.RentalDao;
import model.entities.Client;
import model.entities.Movie;
import model.entities.Rental;

import java.sql.*;

import java.util.ArrayList;

import java.util.List;

public class RentalDaoJdbc implements RentalDao {

    Connection connection = null;

    public RentalDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Rental obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement("INSERT INTO tb_rental (moment, id_media, id_client) " +
                    "VALUES " +
                    "(?, ?, ?)", st.RETURN_GENERATED_KEYS);
            st.setDate(1, new java.sql.Date(obj.getMoment().getTime()));
            st.setInt(2, obj.getMedia().getId());
            st.setInt(3, obj.getClient().getId());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            if (rs.next()){
                obj.setId(rs.getInt(1));
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Rental obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("UPDATE tb_rental " +
                    "SET moment = ?, id_media = ?, id_client = ? " +
                    "WHERE Id = ?");
            st.setDate(1, new java.sql.Date(obj.getMoment().getTime()));
            st.setInt(2, obj.getMedia().getId());
            st.setInt(3, obj.getClient().getId());
            st.setInt(4, obj.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM tb_rental " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            int result = st.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Rental findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_rental " +
                    "WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
                Rental rental = new Rental(DaoFactory.createMediaDao().findById(rs.getInt(3)),
                        DaoFactory.createClientDao().findById(rs.getInt(4)),
                        rs.getDate(2));
                rental.setId(rs.getInt(1));

                return rental;
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Rental> findAll() {

        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("SELECT * FROM " +
                    "tb_rental");
            rs = st.executeQuery();
            List<Rental> rentals = new ArrayList<>();
            int i = 0;
            while (rs.next()){
                rentals.add(new Rental(DaoFactory.createMediaDao().findById(rs.getInt(3))
                        , DaoFactory.createClientDao().findById(rs.getInt(4)),
                        rs.getDate(2)));
                rentals.get(i).setId(rs.getInt(1));
                i++;
            }
            return rentals;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
