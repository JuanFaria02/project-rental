package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.entities.Client;
import model.entities.Movie;
import model.entities.MovieType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoJdbc implements MovieDao {
    private Connection connection;
    public MovieDaoJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Movie insert(Movie obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            connection.setAutoCommit(false);
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
    public Movie update(Movie obj) {
        PreparedStatement st = null;
        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("UPDATE tb_movie " +
                    "SET tittle = ?, director_name = ? " +
                    "WHERE Id = ?");
            st.setString(1, obj.getTittle());
            st.setString(2, obj.getDirector());
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
            st = connection.prepareStatement("DELETE FROM tb_movie " +
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

                for (MovieType t:
                DaoFactory.createMovieTypeDao().findByName(movie.getTittle())) {
                    movie.getTypeSet().add(t.getType());
                }

                return movie;
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
                Movie movie = new Movie(rs.getString(2), rs.getString(3));
                movie.setId(rs.getInt(1));

                for (MovieType t:
                        DaoFactory.createMovieTypeDao().findByName(movie.getTittle())) {
                    movie.getTypeSet().add(t.getType());
                }

                movies.add(movie);
                i++;
            }
            return movies;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Movie> findByDirector(String name) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_movie " +
                    "WHERE director_name = ?");
            st.setString(1, name);
            rs = st.executeQuery();
            List<Movie> movieList = new ArrayList<>();

            while (rs.next()){
                Movie movie = new Movie(rs.getString(2), rs.getString(3));
                movie.setId(rs.getInt(1));
                for (MovieType t:
                        DaoFactory.createMovieTypeDao().findByName(movie.getTittle())) {
                    movie.getTypeSet().add(t.getType());
                }

                movieList.add(movie);

            }
            return movieList;
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
    }

}
