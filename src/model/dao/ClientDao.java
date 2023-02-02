package model.dao;

import model.entities.Client;

import java.util.List;

public interface ClientDao {
    Client insert(Client obj);
    Client update(Client obj);
    void deleteById(Integer id);
    Client findById(Integer id);
    List<Client> findAll();
    Client findByCpf(String cpf);
}
