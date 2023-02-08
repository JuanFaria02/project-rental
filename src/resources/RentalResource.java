package resources;

import com.google.gson.Gson;
import model.entities.Media;
import model.entities.Rental;
import services.MediaServices;
import services.RentalService;

import java.util.List;

public class RentalResource {
    RentalService rentalService = new RentalService();

    public String findAll(){
        List<Rental> rentalList = rentalService.findAll();
        String rentalJson = new Gson().toJson(rentalList);
        return rentalJson;
    }

    public String findById(Integer id){
        Rental rental = rentalService.findById(id);
        String rentalJson = new Gson().toJson(rental);
        return rentalJson;
    }

    public String insert(Rental obj) {

        obj = rentalService.insert(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public boolean deleteById(Integer id) {
        return rentalService.deleteById(id);
    }

    public String update(Rental obj) {
        obj = rentalService.update(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public String findByNameClient(String name) {
        List<Rental> rentalList = rentalService.getByNameClient(name);
        String rentalJson = new Gson().toJson(rentalList);
        return rentalJson;
    }
}
