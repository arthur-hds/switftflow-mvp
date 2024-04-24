package com.arthursouza.swiftflowMVP.services.Relations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;
import com.arthursouza.swiftflowMVP.models.dto.DisponibilityDTO.DisponibilityCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.DisponibilityDTO.DisponibilityUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.Relations.DisponibilityRepository;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class DisponibilityService {
    
    @Autowired
    private DisponibilityRepository disponibilityRepository;


    public Disponibility findById(Long id){
        Optional<Disponibility> disponibility = this.disponibilityRepository.findById(id);
        
        return disponibility.orElseThrow(() -> new ObjectNotFoundException(
            "This ID doesn't match any disponibility. Id: " + id + " Type: "+ Disponibility.class.getName()));


    }


    public List<Disponibility> findAllDisponibilities(){
        List<Disponibility> disponibilities = this.disponibilityRepository.findAllDisponibilities();

        return disponibilities;
    }



    public List<Disponibility> findAllProvidersDisponibilities(Long id){
        List<Disponibility> disponibilities = this.disponibilityRepository.findAllProvidersDisponibilities(id);

        return disponibilities;


    }





    //CRUD METHODS 
    @Transactional
    public Disponibility create(@Valid Disponibility disponibility){
        disponibility.setId(null);
        disponibility = this.disponibilityRepository.save(disponibility);

        return disponibility;


    }


    @Transactional
    public Disponibility update(@Valid Disponibility disponibility){
        Disponibility newDisponibility = findById(disponibility.getId());

        newDisponibility.setPrice(disponibility.getPrice());
        newDisponibility.setProvider_id(disponibility.getProvider_id());
        newDisponibility.setSale(disponibility.getSale());
        newDisponibility.setShirt_id(disponibility.getShirt_id());


        return this.disponibilityRepository.save(newDisponibility);

    }


    @Transactional
    public void delete(@Valid Long id){
        Disponibility disponibility = findById(id);

        try {
            this.disponibilityRepository.delete(disponibility);
        } catch (Exception e) {
            throw new DataBindingViolationException("It's not possible to delete this OrderClient. There are related entities");
        }
    }


    public Disponibility fromDTO(@Valid DisponibilityCreateDTO obj){
        Disponibility disponibility = new Disponibility();

        disponibility.setPrice(obj.getPrice());
        disponibility.setProvider_id(obj.getProvider_id());
        disponibility.setSale(obj.getSale());
        disponibility.setShirt_id(obj.getShirt_id());

        return disponibility;

    }


    public Disponibility fromDTO(@Valid DisponibilityUpdateDTO obj, @Valid Long id){
        Disponibility disponibility = new Disponibility();

        disponibility.setId(obj.getId());
        disponibility.setPrice(obj.getPrice());
        disponibility.setSale(obj.getSale());
        disponibility.setProvider_id(findById(id).getProvider_id());
        disponibility.setShirt_id(obj.getShirt_id());

        return disponibility;

    }

}
