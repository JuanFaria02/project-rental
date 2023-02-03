package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.TypeDao;
import model.entities.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDaoJdbc implements TypeDao {

    private Connection connection;
    public TypeDaoJdbc(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Type insert(Type obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement("INSERT INTO tb_type (name) " +
                    "VALUES " +
                    "(?)", st.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
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
            if (rs != null) {
                DB.closeResultSet(rs);
            }
        }
        return obj;
    }

    @Override
    public Type update(Type obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("UPDATE tb_type " +
                    "SET name = ? " +
                    "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
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
            st = connection.prepareStatement("DELETE FROM tb_type " +
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
    public Type findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_type " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
                Type type = new Type(rs.getString(2));
                type.setId(rs.getInt(1));
                return type;
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
    public List<Type> findAll() {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("SELECT * FROM " +
                    "tb_type");
            rs = st.executeQuery();
            List<Type> types = new ArrayList<>();
            int i = 0;
            while (rs.next()){
                types.add(new Type(rs.getString(2)));
                types.get(i).setId(rs.getInt(1));
                i++;
            }
            return types;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    @Override
    public Type findByName(String name) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("SELECT * FROM tb_type " +
                    "WHERE name = ?");
            st.setString(1, name);
            rs = st.executeQuery();
            if (rs.next()){
                Type type = new Type(rs.getString(2));
                type.setId(rs.getInt(1));
                return type;
            }
            connection.commit();
        }
        catch (SQLException e){
            try {
                connection.rollback();
                throw new DbException("Transaction rolled back. Caused by: " + e.getMessage());
            }
            catch (SQLException e1) {
                throw new DbException("Error trying rolled back. Caused By: " + e1.getMessage());
            }
        }
        finally {

            DB.closeStatement(st);
            if (rs != null) {
                DB.closeResultSet(rs);
            }
        }
        return null;
    }

}

