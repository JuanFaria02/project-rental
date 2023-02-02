package resources;

import com.google.gson.Gson;

import model.entities.Client;
import services.ClientServices;

import java.util.List;


public class ClientResources {
    ClientServices clientServices = new ClientServices();

    public String findAll(){
        List<Client> clientList = clientServices.findAll();
        String clientJson = new Gson().toJson(clientList);
        return clientJson;
    }

    public String findById(Integer id){
        Client client = clientServices.findById(id);
        String clientJson = new Gson().toJson(client);
        return clientJson;
    }

    public String insert(Client obj) {

        obj = clientServices.insert(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public boolean deleteById(Integer id) {
        return clientServices.deleteById(id);
    }

    public String update(Client obj) {
        obj = clientServices.update(obj);
        String objJson = new Gson().toJson(obj);
        return objJson;
    }

    public String findByCpf(String cpf) {
        Client obj = clientServices.findByCpf(cpf);
        return new Gson().toJson(obj);
    }
}
