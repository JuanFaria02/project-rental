package services;

import model.dao.DaoFactory;

import model.dao.RentalDao;

import model.entities.Rental;
import services.exception.ServiceException;

import java.util.List;

public class RentalService {
    RentalDao rentalDao = DaoFactory.createRentalDao();

    public List<Rental> findAll() {
        List<Rental> rentalList = rentalDao.findAll();
        return rentalList;
    }

    public Rental findById(Integer id) {
        if (rentalDao.findById(id) == null) {
            throw new ServiceException("Id doesn't exist");
        }
        Rental rental = rentalDao.findById(id);
        return rental;
    }

    public Rental insert(Rental obj){
        return rentalDao.insert(obj);
    }

    public Rental update(Rental obj) {
        if (!checkIdExist(obj.getId())) {
            throw new ServiceException("Id doesn't exist");
        }
        return rentalDao.update(obj);
    }

    public boolean deleteById(Integer id) {
        if (!checkIdExist(id)) {
            throw new ServiceException("Id doesn't exist");
        }
        rentalDao.deleteById(id);
        return true;
    }

    public List<Rental> getByNameClient(String name) {
        List<Rental> rentals = rentalDao.findByNameClient(name);
        return rentals;
    }


    private boolean checkIdExist(Integer id) {
        List<Rental> rentalList = rentalDao.findAll();
        for (Rental r :
                rentalList) {
            if (r.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
