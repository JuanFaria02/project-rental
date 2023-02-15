package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.MediaDao;
import model.dao.MovieDao;
import model.entities.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MediaDaoJdbc implements MediaDao {
    private Connection connection;
    public MediaDaoJdbc(Connection connection){
        this.connection = connection;
    }
    @Override
    public Media insert(Media obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("INSERT INTO tb_media (cod_bar, id_movie) " +
                    "VALUES " +
                    "(?, ?)", st.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getCodeBar());
            st.setInt(2, obj.getMovie().getId());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            if (rs.next()){
                obj.setId(rs.getInt(1));
            }
            connection.commit();
        }
        catch (SQLException e){
            try {
                connection.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            }
            catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            if (rs != null) {
                DB.closeResultSet(rs);
            }
        }
        return obj;
    }

    @Override
    public Media update(Media obj) {
        PreparedStatement st = null;
        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("UPDATE tb_media " +
                    "SET cod_bar = ?, Id_movie = ? " +
                    "WHERE Id = ?");
            st.setString(1, obj.getCodeBar());
            st.setInt(2, obj.getMovie().getId());
            st.setInt(3, obj.getId());
            st.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            try {
                connection.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            }
            catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
        }
        return obj;
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            connection.setAutoCommit(false);
            st = connection.prepareStatement("DELETE FROM tb_media " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            int result = st.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            try {
                connection.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            }
            catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Media findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_media " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            MovieDao movieDao = DaoFactory.createMovieDao();
            if (rs.next()){
                Media media = new Media();
                media.setCodeBar(rs.getString(2));
                media.setMovie(movieDao.findById(rs.getInt(3)));
                media.setId(rs.getInt(1));
                return media;
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            if (rs != null) {
                DB.closeResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Media> findAll() {
        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("SELECT * FROM " +
                    "tb_media");
            rs = st.executeQuery();
            List<Media> mediaList = new ArrayList<>();
            MovieDao movieDao = DaoFactory.createMovieDao();

            int i = 0;
            while (rs.next()){
                Media media = new Media();
                media.setCodeBar(rs.getString(2));
                media.setMovie(movieDao.findById(rs.getInt(3)));
                mediaList.add(media);
                mediaList.get(i).setId(rs.getInt(1));
                i++;
            }
            return mediaList;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
