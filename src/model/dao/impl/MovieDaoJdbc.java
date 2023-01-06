package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.MovieDao;
import model.entities.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoJdbc implements MovieDao {
    private Connection connection;
    public MovieDaoJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Movie obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement("INSERT INTO tb_movie (tittle, director_name) " +
                    "VALUES " +
                    "(?, ?)", st.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getTittle());
            st.setString(2, obj.getDirector());
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
    public void update(Movie obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("UPDATE tb_movie " +
                    "SET tittle = ?, director_name = ? " +
                    "WHERE Id = ?");
            st.setString(1, obj.getTittle());
            st.setString(2, obj.getDirector());
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
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM tb_movie " +
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
    public Movie findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_movie " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
                Movie movie = new Movie(rs.getString(2), rs.getString(3));
                movie.setId(rs.getInt(1));
                return movie;
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
    public List<Movie> findAll() {
        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("SELECT * FROM " +
                    "tb_movie");
            rs = st.executeQuery();
            List<Movie> movies = new ArrayList<>();
            int i = 0;
            while (rs.next()){
                movies.add(new Movie(rs.getString(2), rs.getString(3)));
                movies.get(i).setId(rs.getInt(1));
                i++;
            }
            return movies;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
