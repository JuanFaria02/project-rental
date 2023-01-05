package model.dao;

import model.entities.Rental;
import model.entities.Type;

import java.util.List;

public interface RentalDao {
    void insert(Rental obj);
    void update(Rental obj);
    void deleteById(Integer id);
    Rental findById(Integer id);
    List<Rental> findAll();
}
