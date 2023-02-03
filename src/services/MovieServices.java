package services;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.entities.Client;
import model.entities.Movie;
import services.exception.ServiceException;

import java.util.List;

public class MovieServices {
    MovieDao movieDao = DaoFactory.createMovieDao();

    public List<Movie> findAll() {
        List<Movie> listMovie = movieDao.findAll();
        return listMovie;
    }

    public Movie findById(Integer id) {
        if (movieDao.findById(id) == null) {
            throw new ServiceException("Id doesn't exist");
        }
        Movie movie = movieDao.findById(id);
        return movie;
    }

    public Movie insert(Movie obj){
        return movieDao.insert(obj);
    }

    public Movie update(Movie obj) {
        if (!checkIdExist(obj.getId())) {
            throw new ServiceException("Id doesn't exist");
        }
        return movieDao.update(obj);
    }

    public boolean deleteById(Integer id) {
        if (!checkIdExist(id)) {
            throw new ServiceException("Id doesn't exist");
        }
        movieDao.deleteById(id);
        return true;
    }

    public List<Movie> findByDirector(String name) {
        if (movieDao.findByDirector(name).isEmpty()) {
            throw new ServiceException("Movie doesn't exist");
        }
        return movieDao.findByDirector(name);
    }

    private boolean checkIdExist(Integer id) {
        List<Movie> movieList = movieDao.findAll();
        for (Movie m :
                movieList) {
            if (m.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
