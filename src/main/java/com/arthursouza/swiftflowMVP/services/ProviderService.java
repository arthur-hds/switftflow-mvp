package com.arthursouza.swiftflowMVP.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Provider;
import com.arthursouza.swiftflowMVP.repositories.ProviderRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ProviderService {
    

    @Autowired
    private ProviderRepository providerRepository;


    public Provider findById(Long id){
        Optional<Provider> provider = this.providerRepository.findById(id);

        return provider.orElseThrow(() -> new RuntimeException(
            "This ID doesn't match any provider. Id: " + id + " Type: "+ Provider.class.getName()));
    }

    //CRUD METHODS

    @Transactional
    public Provider create(Provider obj){
        obj.setId(null);
        obj = this.providerRepository.save(obj);

        return obj;
    }


    @Transactional
    public Provider update(Provider obj){
        Provider newObject = findById(obj.getId());

        newObject.setDelivery(obj.getDelivery());
        newObject.setMinimum(obj.getMinimum());
        newObject.setName(obj.getName());

        return this.providerRepository.save(newObject);
    }


    @Transactional
    public void delete(Long id){
        Provider obj = findById(id);

        try {
            this.providerRepository.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("It's not possible to delete this provider. There are related entities");
        }



    }

}
