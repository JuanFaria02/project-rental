package model.dao;

import model.entities.Rental;

import java.util.List;

public interface RentalDao {
    Rental insert(Rental obj);
    Rental update(Rental obj);
    void deleteById(Integer id);
    Rental findById(Integer id);
    List<Rental> findAll();
    List<Rental> findByNameClient(String name);

}
