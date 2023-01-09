package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.MediaDao;
import model.entities.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MediaDaoJdbc implements MediaDao {
    private Connection connection;
    public MediaDaoJdbc(Connection connection){
        this.connection = connection;
    }
    @Override
    public void insert(Media obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement("INSERT INTO tb_media (cod_bar, Id_movie) " +
                    "VALUES " +
                    "(?, ?)", st.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getCodeBar());
            st.setInt(2, obj.getMovie().getId());
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
    public void update(Media obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("UPDATE tb_media " +
                    "SET cod_bar = ?, Id_movie = ? " +
                    "WHERE Id = ?");
            st.setString(1, obj.getCodeBar());
            st.setInt(2, obj.getMovie().getId());
            st.setInt(3, obj.getId());
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

    }

    @Override
    public Media findById(Integer id) {
        return null;
    }

    @Override
    public List<Media> findAll() {
        return null;
    }
}
