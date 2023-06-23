package service;

import entity.ClientEntity;
import repository.ClientRepository;

import java.util.List;

public class ClientCrudService {

    private ClientRepository clientRepository;

    public ClientCrudService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientEntity> findAll(){
        return clientRepository.findAll();
    }

    public ClientEntity findById(Long id){
        return clientRepository.findById(id);
    }

    public ClientEntity save(ClientEntity entity){
        return clientRepository.save(entity);
    }

    public int deleteById (Long id){
         return clientRepository.deleteById(id);
    }

    public ClientEntity update (ClientEntity entity){
        return clientRepository.update(entity);
    }
}
