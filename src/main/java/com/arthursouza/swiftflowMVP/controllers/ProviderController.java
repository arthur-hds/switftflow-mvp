package com.arthursouza.swiftflowMVP.controllers;

import java.net.URI;
import java.util.List;

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

import com.arthursouza.swiftflowMVP.models.Provider;
import com.arthursouza.swiftflowMVP.models.dto.ProviderDTO.ProviderCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.ProviderDTO.ProviderUpdateDTO;
import com.arthursouza.swiftflowMVP.services.ProviderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/provider")
@Validated
public class ProviderController {
    
    @Autowired
    private ProviderService providerService;


    @GetMapping("/{id}")
    public ResponseEntity<Provider> findById(@PathVariable Long id){
        Provider obj = this.providerService.findById(id);

        return ResponseEntity.ok().body(obj);

    }

    @GetMapping
    public ResponseEntity<List<Provider>> findAllProviders(){
        List<Provider> providers = this.providerService.findAllProviders();

        return ResponseEntity.ok().body(providers);
    }

    
    //CRUD METHODS
    
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ProviderCreateDTO obj){
        Provider provider = this.providerService.fromDTO(obj);
        this.providerService.create(provider);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(provider.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ProviderUpdateDTO obj, @PathVariable Long id){
        Provider provider = this.providerService.fromDTO(obj);
        provider.setId(id);

        this.providerService.update(provider);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.providerService.delete(id);

        return ResponseEntity.noContent().build();

    }


}
