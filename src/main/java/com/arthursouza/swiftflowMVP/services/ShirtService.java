package com.arthursouza.swiftflowMVP.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Shirt;
import com.arthursouza.swiftflowMVP.models.dto.ShirtDTO.ShirtCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.ShirtDTO.ShirtUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.ShirtRepository;

import jakarta.validation.Valid;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ShirtService {
    
    @Autowired
    private ShirtRepository shirtRepository;


    public Shirt findbyId(Long id){
        Optional<Shirt> shirt = this.shirtRepository.findById(id);

        return shirt.orElseThrow(() -> new RuntimeException(
            "This ID doesn't match any shirt. Id: " + id + " Type: "+ Shirt.class.getName()));

    }


    //CRUD METHODS

    @Transactional
    public Shirt create(Shirt obj){
        obj.setId(null);
        obj = this.shirtRepository.save(obj);

        return obj;
    }

    
    @Transactional
    public Shirt update(Shirt obj){
        Shirt newObj = findbyId(obj.getId());

        newObj.setSeason(obj.getSeason());
        newObj.setTeam_id(obj.getTeam_id());
        newObj.setType(obj.getType());

        return this.shirtRepository.save(newObj);


    }


    @Transactional
    public void delete(Long id){
        Shirt obj = findbyId(id);

        try {
            this.shirtRepository.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("It's not possible to delete this client. There are related entities");
        }


    }



    public Shirt fromDTO(@Valid ShirtCreateDTO obj){
        Shirt shirt = new Shirt();
        shirt.setSeason(obj.getSeason());
        shirt.setTeam_id(obj.getTeam_id());
        shirt.setType(obj.getType());

        return shirt;
    }


    public Shirt fromDTO(@Valid ShirtUpdateDTO obj,@Valid Long id){
        Shirt shirt = new Shirt();
        shirt.setId(obj.getId());
        shirt.setSeason(obj.getSeason());
        shirt.setType(obj.getType());
        shirt.setTeam_id(findbyId(id).getTeam_id());

        return shirt;
    }


}
