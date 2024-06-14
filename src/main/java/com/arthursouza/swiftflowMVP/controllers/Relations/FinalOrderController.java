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

import com.arthursouza.swiftflowMVP.models.Relations.FinalOrder;
import com.arthursouza.swiftflowMVP.models.dto.FinalOrderDTO.FinalOrderCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.FinalOrderDTO.FinalOrderUpdateDTO;
import com.arthursouza.swiftflowMVP.services.Relations.FinalOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/finalOrder")
@Validated
public class FinalOrderController {
    
    @Autowired
    private FinalOrderService finalOrderService;


    @GetMapping("/{id}")
    public ResponseEntity<FinalOrder> findById(@Valid @PathVariable Long id){
        FinalOrder finalOrder = this.finalOrderService.findById(id);

        return ResponseEntity.ok().body(finalOrder);

    }

    //CRUD METHODS

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody FinalOrderCreateDTO obj){
        FinalOrder finalOrder = this.finalOrderService.fromDTO(obj);

        this.finalOrderService.create(finalOrder);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
        path("{id}").buildAndExpand(finalOrder.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody FinalOrderUpdateDTO obj, @Valid @PathVariable Long id){
        FinalOrder finalOrder = this.finalOrderService.fromDTO(obj);

        finalOrder.setId(id);
        this.finalOrderService.update(finalOrder);

        return ResponseEntity.noContent().build();


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.finalOrderService.delete(id);

        return ResponseEntity.noContent().build();


    }



}
