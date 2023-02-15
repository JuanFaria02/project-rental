package services;

import model.dao.DaoFactory;
import model.dao.MovieTypeDao;
import model.entities.MovieType;
import services.exception.ServiceException;

import java.util.List;

public class MovieTypeService {
    MovieTypeDao movieTypeDao = DaoFactory.createMovieTypeDao();

    public List<MovieType> findAll() {
        List<MovieType> movieTypeList = movieTypeDao.findAll();
        return movieTypeList;
    }

    public MovieType findById(Integer id) {
        if (movieTypeDao.findById(id) == null) {
            throw new ServiceException("Id doesn't exist");
        }
        MovieType movieType = movieTypeDao.findById(id);
        return movieType;
    }

    public MovieType insert(MovieType obj){
        return movieTypeDao.insert(obj);
    }

    public MovieType update(MovieType obj) {
        if (!checkIdExist(obj.getId())) {
            throw new ServiceException("Id doesn't exist");
        }
        return movieTypeDao.update(obj);
    }

    public boolean deleteByIdMovie(Integer id) {

        movieTypeDao.deleteByIdMovie(id);
        return true;
    }


    public List<MovieType> findByName(String name) {
        if (movieTypeDao.findByName(name) == null) {
            throw new ServiceException("Movie name doesn't exist");
        }
        List<MovieType> movieTypeList = movieTypeDao.findByName(name);
        return movieTypeList;
    }


    private boolean checkIdExist(Integer id) {
        List<MovieType> movieTypeList = movieTypeDao.findAll();
        for (MovieType t :
                movieTypeList) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
