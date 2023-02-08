package services;

import model.dao.DaoFactory;
import model.dao.MediaDao;

import model.entities.Media;

import services.exception.ServiceException;

import java.util.List;

public class MediaServices {
    MediaDao mediaDao = DaoFactory.createMediaDao();

    public List<Media> findAll() {
        List<Media> mediaList = mediaDao.findAll();
        return mediaList;
    }

    public Media findById(Integer id) {
        if (mediaDao.findById(id) == null) {
            throw new ServiceException("Id doesn't exist");
        }
        Media media = mediaDao.findById(id);
        return media;
    }

    public Media insert(Media obj){
        return mediaDao.insert(obj);
    }

    public Media update(Media obj) {
        if (!checkIdExist(obj.getId())) {
            throw new ServiceException("Id doesn't exist");
        }
        return mediaDao.update(obj);
    }

    public boolean deleteById(Integer id) {
        if (!checkIdExist(id)) {
            throw new ServiceException("Id doesn't exist");
        }
        mediaDao.deleteById(id);
        return true;
    }

    private boolean checkIdExist(Integer id) {
        List<Media> mediaList = mediaDao.findAll();
        for (Media m :
                mediaList) {
            if (m.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
