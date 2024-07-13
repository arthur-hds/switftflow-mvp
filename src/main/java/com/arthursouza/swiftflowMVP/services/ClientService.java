package com.arthursouza.swiftflowMVP.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Client;
import com.arthursouza.swiftflowMVP.models.dto.ClientDTO.ClientCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.ClientDTO.ClientUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.ClientRepository;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;



    public Client findById(Long id){
        Optional<Client> client = this.clientRepository.findById(id);

        return client.orElseThrow(() -> new ObjectNotFoundException(
            "This ID doesn't match any client. Id: " + id + " Type: "+ Client.class.getName()));
    }


    public List<Client> findAllClients(){
        List<Client> clients = this.clientRepository.findAllClients();
        return clients;

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
            throw new DataBindingViolationException("It's not possible to delete this client. There are related entities");
        }
        
    }



    public Client fromDTO(@Valid ClientCreateDTO obj){
        Client client = new Client();

        client.setName(obj.getName());
        client.setNumber(obj.getNumber());
        client.setType(obj.getType());

        return client;
    }


    public Client fromDTO(@Valid ClientUpdateDTO obj){
        Client client = new Client();

        client.setId(obj.getId());
        client.setName(obj.getName());
        client.setNumber(obj.getNumber());
        client.setType(obj.getType());

        return client;


    }


}
