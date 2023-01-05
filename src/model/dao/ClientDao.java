package model.dao;

import model.entities.Client;

import java.util.List;

public interface ClientDao {
    void insert(Client obj);
    void update(Client obj);
    void deleteById(Integer id);
    Client findById(Integer id);
    List<Client> findAll();
}
