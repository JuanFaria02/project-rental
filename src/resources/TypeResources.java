package resources;

import com.google.gson.Gson;
import model.entities.Movie;
import model.entities.Type;
import services.TypeServices;

import java.util.List;

public class TypeResources {
    TypeServices typeServices = new TypeServices();

    public String findAll(){
        List<Type> typeList = typeServices.findAll();
        String  typeJson = new Gson().toJson(typeList);
        return typeJson;
    }

    public String findById(Integer id){
        Type type = typeServices.findById(id);
        String typeJson = new Gson().toJson(type);
        return typeJson;
    }

    public String insert(Type obj) {

        obj = typeServices.insert(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public boolean deleteById(Integer id) {
        return typeServices.deleteById(id);
    }

    public String update(Type obj) {
        obj = typeServices.update(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public String findByName(String name) {
        Type obj = typeServices.findByName(name);
        return new Gson().toJson(obj);
    }

}
