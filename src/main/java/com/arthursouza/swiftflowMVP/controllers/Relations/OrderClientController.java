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

import com.arthursouza.swiftflowMVP.models.Relations.OrderClient;
import com.arthursouza.swiftflowMVP.models.dto.OrderClientDTO.OrderClientCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.OrderClientDTO.OrderClientUpdateDTO;
import com.arthursouza.swiftflowMVP.services.Relations.OrderClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderClient")
@Validated
public class OrderClientController {
    
    @Autowired
    private OrderClientService orderClientService;


    @GetMapping("/{id}")
    public ResponseEntity<OrderClient> findById(@Valid @PathVariable Long id){
        OrderClient orderClient = this.orderClientService.findById(id);

        return ResponseEntity.ok().body(orderClient);
    }


    //CRUD METHODS

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody OrderClientCreateDTO obj){
        OrderClient orderClient = this.orderClientService.fromDTO(obj);

        this.orderClientService.create(orderClient);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(orderClient.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody OrderClientUpdateDTO obj, @Valid @PathVariable Long id){
        OrderClient orderClient = this.orderClientService.fromDTO(obj);

        orderClient.setId(id);
        this.orderClientService.update(orderClient);

        return ResponseEntity.noContent().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.orderClientService.delete(id);

        return ResponseEntity.noContent().build();

    }



}
