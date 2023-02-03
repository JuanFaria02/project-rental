package services;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.dao.TypeDao;
import model.entities.Movie;
import model.entities.Type;
import services.exception.ServiceException;

import java.util.List;

public class TypeServices {
    TypeDao typeDao = DaoFactory.createTypeDao();

    public List<Type> findAll() {
        List<Type> typeList = typeDao.findAll();
        return typeList;
    }

    public Type findById(Integer id) {
        if (typeDao.findById(id) == null) {
            throw new ServiceException("Id doesn't exist");
        }
        Type type = typeDao.findById(id);
        return type;
    }

    public Type insert(Type obj){
        return typeDao.insert(obj);
    }

    public Type update(Type obj) {
        if (!checkIdExist(obj.getId())) {
            throw new ServiceException("Id doesn't exist");
        }
        return typeDao.update(obj);
    }

    public boolean deleteById(Integer id) {
        if (!checkIdExist(id)) {
            throw new ServiceException("Id doesn't exist");
        }
        typeDao.deleteById(id);
        return true;
    }

    public Type findByName(String name) {
        if (typeDao.findByName(name) == null) {
            throw new ServiceException("Type name doesn't exist");
        }
        return typeDao.findByName(name);
    }

    private boolean checkIdExist(Integer id) {
        List<Type> typeList = typeDao.findAll();
        for (Type t :
                typeList) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
