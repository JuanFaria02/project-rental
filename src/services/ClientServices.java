package services;



import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;
import services.exception.ServiceException;

import java.util.List;


public class ClientServices {

    ClientDao clientDao = DaoFactory.createClientDao();

    public List<Client> findAll() {
        List<Client> listClient = clientDao.findAll();
        return listClient;
    }

    public Client findById(Integer id) {
        if (clientDao.findById(id) == null) {
            throw new ServiceException("Id doesn't exist");
        }
        Client client = clientDao.findById(id);
        return client;
    }

    public Client insert(Client obj){
        return clientDao.insert(obj);
    }

    public Client update(Client obj) {
        if (!checkIdExist(obj.getId())) {
            throw new ServiceException("Id doesn't exist");
        }
        return clientDao.update(obj);
    }

    public boolean deleteById(Integer id) {
        if (!checkIdExist(id)) {
            throw new ServiceException("Id doesn't exist");
        }
        clientDao.deleteById(id);
        return true;
    }

    public Client findByCpf(String cpf) {
        if (clientDao.findByCpf(cpf) == null) {
            throw new ServiceException("Cpf doesn't exist");
        }
        return clientDao.findByCpf(cpf);
    }

    private boolean checkIdExist(Integer id) {
        List<Client> listClient = clientDao.findAll();
        for (Client c :
                listClient) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
