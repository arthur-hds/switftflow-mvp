package com.arthursouza.swiftflowMVP.controllers.Relations;

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

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;
import com.arthursouza.swiftflowMVP.models.dto.DisponibilityDTO.DisponibilityCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.DisponibilityDTO.DisponibilityUpdateDTO;
import com.arthursouza.swiftflowMVP.services.Relations.DisponibilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disponibility")
@Validated
public class DisponibilityController {
    
    @Autowired
    private DisponibilityService disponibilityService;

    @GetMapping("/{id}")
    public ResponseEntity<Disponibility> findById(@Valid @PathVariable Long id){
        Disponibility disponibility = this.disponibilityService.findById(id);

        return ResponseEntity.ok().body(disponibility);
    }


    //CRUD METHODS

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody DisponibilityCreateDTO obj){
        Disponibility disponibility = this.disponibilityService.fromDTO(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(disponibility.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody DisponibilityUpdateDTO obj, @Valid @PathVariable Long id){
        Disponibility disponibility = this.disponibilityService.fromDTO(obj);

        disponibility.setId(id);
        this.disponibilityService.update(disponibility);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.disponibilityService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
