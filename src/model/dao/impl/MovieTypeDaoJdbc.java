package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.dao.MovieTypeDao;
import model.dao.TypeDao;
import model.entities.Movie;
import model.entities.MovieType;
import model.entities.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieTypeDaoJdbc implements MovieTypeDao {
    private Connection connection;
    public MovieTypeDaoJdbc(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(MovieType obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement("INSERT INTO tb_movie_type (Id_movie, Id_type) " +
                    "VALUES " +
                    "(?, ?)", st.RETURN_GENERATED_KEYS);
            st.setInt(1, obj.getMovie().getId());
            st.setInt(2, obj.getType().getId());
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
    public void update(MovieType obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("UPDATE tb_movie_type " +
                    "SET Id_movie = ?, Id_type = ? " +
                    "WHERE Id = ?");
            st.setInt(1, obj.getMovie().getId());
            st.setInt(2, obj.getType().getId());
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
            st = connection.prepareStatement("DELETE FROM tb_movie_type " +
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
    public MovieType findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_movie_type " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            MovieDao movieDao = DaoFactory.createMovieDao();
            TypeDao typeDao = DaoFactory.createTypeDao();

            if (rs.next()){
                Movie movie = movieDao.findById(rs.getInt(2));
                Type type = typeDao.findById(rs.getInt(3));
                MovieType movieType = new MovieType(movie, type);
                movieType.setId(rs.getInt(1));
                return movieType;
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
    public List<MovieType> findAll() {
        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("SELECT * FROM " +
                    "tb_movie_type");
            rs = st.executeQuery();
            List<MovieType> movieTypes = new ArrayList<>();
            int i = 0;
            while (rs.next()){
                movieTypes.add(new MovieType(DaoFactory.createMovieDao().findById(rs.getInt(2)), DaoFactory.createTypeDao().findById(rs.getInt(3))));
                movieTypes.get(i).setId(rs.getInt(1));
                i++;
            }
            return movieTypes;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
