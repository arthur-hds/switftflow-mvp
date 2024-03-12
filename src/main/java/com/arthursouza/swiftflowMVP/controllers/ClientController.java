package com.arthursouza.swiftflowMVP.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arthursouza.swiftflowMVP.models.Client;
import com.arthursouza.swiftflowMVP.models.dto.ClientDTO.ClientCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.ClientDTO.ClientUpdateDTO;
import com.arthursouza.swiftflowMVP.services.ClientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/client")
@Validated
public class ClientController {
    
    @Autowired
    private ClientService clientService;



    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        Client obj = this.clientService.findById(id);

        return ResponseEntity.ok().body(obj);        
    }

    //CRUD METHODS

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ClientCreateDTO obj){
        Client client = this.clientService.fromDTO(obj);

        this.clientService.create(client);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> udpate(@Valid @RequestBody ClientUpdateDTO obj, @PathVariable Long id){
        Client client = this.clientService.fromDTO(obj);

        client.setId(id);
        this.clientService.update(client);

        return ResponseEntity.noContent().build();


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.clientService.delete(id);

        return ResponseEntity.noContent().build();


    }





}
