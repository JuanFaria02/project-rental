package resources;

import com.google.gson.Gson;
import model.entities.Media;
import services.MediaServices;


import java.util.List;

public class MediaResource {
    MediaServices mediaServices = new MediaServices();

    public String findAll(){
        List<Media> mediaList = mediaServices.findAll();
        String mediaJson = new Gson().toJson(mediaList);
        return mediaJson;
    }

    public String findById(Integer id){
        Media media = mediaServices.findById(id);
        String mediaJson = new Gson().toJson(media);
        return mediaJson;
    }

    public String insert(Media obj) {

        obj = mediaServices.insert(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public boolean deleteById(Integer id) {
        return mediaServices.deleteById(id);
    }

    public String update(Media obj) {
        obj = mediaServices.update(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }
}
