package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ClientDao;
import model.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoJdbc implements ClientDao {

    private Connection connection;

    public ClientDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client insert(Client obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("INSERT INTO tb_client (name, cpf) " +
                    "VALUES " +
                    "(?, ?)", st.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getCpf());
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
    public Client update(Client obj) {

        PreparedStatement st = null;
        try{
            connection.setAutoCommit(false);
            st = connection.prepareStatement("UPDATE tb_client " +
                    "SET Cpf = ?, Name = ? " +
                    "WHERE Id = ?");
            st.setString(1, obj.getCpf());
            st.setString(2, obj.getName());
            st.setInt(3, obj.getId());
            st.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            try {
                connection.rollback();
                throw new DbException("Error: " + e.getMessage());
            }
            catch (SQLException e1) {
                throw new DbException("Error: " + e1.getMessage());
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
            st = connection.prepareStatement("DELETE FROM tb_client " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            int result = st.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            try {
                connection.rollback();
                throw new DbException("Error: " + e.getMessage());
            }
            catch (SQLException e1) {
                throw new DbException("Error: " + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Client findById(Integer id) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_client " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
                Client client = new Client(rs.getString(2), rs.getString(3));
                client.setId(rs.getInt(1));
                return client;
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
    public List<Client> findAll() {
        ResultSet rs = null;
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("SELECT * FROM " +
                    "tb_client");
            rs = st.executeQuery();
            List<Client> clients = new ArrayList<>();
            int i = 0;
            while (rs.next()){
                clients.add(new Client(rs.getString(2), rs.getString(3)));
                clients.get(i).setId(rs.getInt(1));
                i++;
            }
            return clients;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public Client findByCpf(String cpf) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("SELECT * FROM tb_client " +
                    "WHERE cpf = ?");
            st.setString(1, cpf);
            rs = st.executeQuery();
            if (rs.next()){
                Client client = new Client(rs.getString(2), rs.getString(3));
                client.setId(rs.getInt(1));
                return client;
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
}