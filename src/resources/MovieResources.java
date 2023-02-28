package resources;

import com.google.gson.Gson;

import model.entities.Movie;

import services.MovieServices;

import java.util.List;

public class MovieResources {
    MovieServices movieServices = new MovieServices();

    public String findAll(){
        List<Movie> movieList = movieServices.findAll();
        String movieJson = new Gson().toJson(movieList);
        return movieJson;
    }

    public String findById(Integer id){
        Movie movie = movieServices.findById(id);
        String movieJson = new Gson().toJson(movie);
        return movieJson;
    }

    public String insert(Movie obj) {

        obj = movieServices.insert(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public boolean deleteById(Integer id) {
        return movieServices.deleteById(id);
    }

    public String update(Movie obj) {
        obj = movieServices.update(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public String findByDirector(String name) {
        List<Movie> obj = movieServices.findByDirector(name);
        return new Gson().toJson(obj);
    }
}
