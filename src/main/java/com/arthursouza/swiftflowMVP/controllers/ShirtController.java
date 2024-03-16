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

import com.arthursouza.swiftflowMVP.models.Shirt;
import com.arthursouza.swiftflowMVP.models.dto.ShirtDTO.ShirtCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.ShirtDTO.ShirtUpdateDTO;
import com.arthursouza.swiftflowMVP.services.ShirtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shirt")
@Validated
public class ShirtController {

    @Autowired
    private ShirtService shirtService;

    @GetMapping("/{id}")
    public ResponseEntity<Shirt> findById(@PathVariable Long id){
        Shirt shirt = this.shirtService.findbyId(id);

        return ResponseEntity.ok().body(shirt);
    }

    
    //CRUD METHODS
    
    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody ShirtCreateDTO obj){
        Shirt shirt = this.shirtService.fromDTO(obj);
        this.shirtService.create(shirt);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(shirt.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    //! Invalid update method. ERROR on null parameters
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody ShirtUpdateDTO obj, @PathVariable Long id){
        Shirt shirt = this.shirtService.fromDTO(obj, id);
        shirt.setId(id);

        this.shirtService.update(shirt);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.shirtService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
