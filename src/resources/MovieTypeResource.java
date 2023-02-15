package resources;

import com.google.gson.Gson;
import model.entities.MovieType;
import services.MovieTypeService;


import java.util.List;

public class MovieTypeResource {
    MovieTypeService movieTypeService = new MovieTypeService();
    public String findAll(){
        List<MovieType> movieTypeList = movieTypeService.findAll();
        String movieTypeJson = new Gson().toJson(movieTypeList);
        return movieTypeJson;
    }

    public String findById(Integer id){
        MovieType movieType = movieTypeService.findById(id);
        String movieTypeJson = new Gson().toJson(movieType);
        return movieTypeJson;
    }

    public String insert(MovieType obj) {

        obj = movieTypeService.insert(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public boolean deleteById(Integer id) {
        return movieTypeService.deleteByIdMovie(id);
    }

    public String update(MovieType obj) {
        obj = movieTypeService.update(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public String findByName(String name) {
        List<MovieType> obj = movieTypeService.findByName(name);
        return new Gson().toJson(obj);
    }

}
