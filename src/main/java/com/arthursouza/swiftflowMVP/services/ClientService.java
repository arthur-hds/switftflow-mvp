package com.arthursouza.swiftflowMVP.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Client;
import com.arthursouza.swiftflowMVP.repositories.ClientRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;



    public Client findById(Long id){
        Optional<Client> client = this.clientRepository.findById(id);

        return client.orElseThrow(() -> new RuntimeException(
            "This ID doesn't match any client. Id: " + id + " Type: "+ Client.class.getName()));
    }



    //CRUD METHODS
    @Transactional
    public Client create(Client client){
        client.setId(null);
        client = this.clientRepository.save(client);
        return client;

    }

    @Transactional
    public Client update(Client client){
        Client newClient = findById(client.getId());

        newClient.setName(client.getName());
        newClient.setNumber(client.getNumber());
        newClient.setType(client.getType());

        return this.clientRepository.save(newClient);

    }


    @Transactional
    public void delete(Long id){
        Client newClient = findById(id);

        try{
            this.clientRepository.delete(newClient);;
        }catch(Exception ex){
            throw new RuntimeException("It's not possible to delete this client. There are related entities");
        }
        
    }

}
