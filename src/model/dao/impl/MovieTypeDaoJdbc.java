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
    public MovieType insert(MovieType obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("INSERT INTO tb_movie_type (id_movie, id_type) " +
                    "VALUES " +
                    "(?, ?)", st.RETURN_GENERATED_KEYS);
            st.setInt(1, obj.getMovie().getId());
            st.setInt(2, obj.getType().getId());
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
    public MovieType update(MovieType obj) {
        PreparedStatement st = null;
        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("UPDATE tb_movie_type " +
                    "SET id_movie = ?, id_type = ? " +
                    "WHERE id = ?");
            st.setInt(1, obj.getMovie().getId());
            st.setInt(2, obj.getType().getId());
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
    public void deleteByIdMovie(Integer id) {
        PreparedStatement st = null;
        try {
            connection.setAutoCommit(false);
            st = connection.prepareStatement("DELETE FROM tb_movie_type " +
                    "WHERE id_movie = ?");
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
    public MovieType findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_movie_type " +
                    "WHERE id = ?");
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
            if (rs != null) {
                DB.closeResultSet(rs);
            }

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

    @Override
    public List<MovieType> findByName(String name) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{

            st = connection.prepareStatement("select tb_movie_type.id, tb_movie_type.id_movie, tb_movie_type.id_type " +
                    "from tb_movie_type inner join tb_movie on tb_movie_type.id_movie = tb_movie.id " +
                    "where tb_movie.tittle = ?");

            st.setString(1, name);
            rs = st.executeQuery();

            List<MovieType> movieTypeList = new ArrayList<>();

            int i = 0;

            while (rs.next()){
                Movie movie = DaoFactory.createMovieDao().findById(rs.getInt(2));
                MovieType movieType = new MovieType(movie,
                        DaoFactory.createTypeDao().findById(rs.getInt(3)));

                movie.getTypeSet().add(DaoFactory.createTypeDao().findById(rs.getInt(3)));

                movieType.setId(rs.getInt(1));
                movieTypeList.add(movieType);
                i++;
            }


            return movieTypeList;
        }
        catch (SQLException e){
                throw new DbException("Transaction rolled back. Caused by: " + e.getMessage());
        }
        finally {

            DB.closeStatement(st);
            if (rs != null) {
                DB.closeResultSet(rs);
            }
        }
    }

    @Override
    public List<MovieType> findByIdMovie(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{

            st = connection.prepareStatement("SELECT tb_movie_type.id, tb_movie_type.id_type" +
                    " FROM tb_movie_type " +
                    "where tb_movie_type.id_movie = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            List<MovieType> movieTypeList = new ArrayList<>();

            int i = 0;
            Movie movie = DaoFactory.createMovieDao().findById(id);
            while (rs.next()){

                MovieType movieType = new MovieType(movie,
                        DaoFactory.createTypeDao().findById(rs.getInt(3)));

                movie.getTypeSet().add(DaoFactory.createTypeDao().findById(rs.getInt(3)));

                movieType.setId(rs.getInt(1));
                movieTypeList.add(movieType);
                i++;
            }


            return movieTypeList;
        }
        catch (SQLException e){
            throw new DbException("Transaction rolled back. Caused by: " + e.getMessage());
        }
        finally {

            DB.closeStatement(st);
            if (rs != null) {
                DB.closeResultSet(rs);
            }
        }
    }

    @Override
    public List<Type> findTypeMovieByIdMovie(Integer idMovie) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{

            st = connection.prepareStatement("SELECT tb_movie_type.id_type" +
                    " FROM tb_movie_type " +
                    "where tb_movie_type.id_movie = ?");

            st.setInt(1, idMovie);
            rs = st.executeQuery();

            List<Type> typeList = new ArrayList<>();

            int i = 0;

            while (rs.next()){

                Type type = DaoFactory.createTypeDao().findById(rs.getInt(1));
                typeList.add(type);
                i++;
            }


            return typeList;
        }
        catch (SQLException e){
            throw new DbException("Transaction rolled back. Caused by: " + e.getMessage());
        }
        finally {

            DB.closeStatement(st);
            if (rs != null) {
                DB.closeResultSet(rs);
            }
        }
    }

    @Override
    public void deleteByIdType(Integer id) {
        PreparedStatement st = null;
        try {
            connection.setAutoCommit(false);
            st = connection.prepareStatement("DELETE FROM tb_movie_type " +
                    "WHERE id_type = ?");
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


}
