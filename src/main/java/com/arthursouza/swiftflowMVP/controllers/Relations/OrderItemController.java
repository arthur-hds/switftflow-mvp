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

import com.arthursouza.swiftflowMVP.models.Relations.OrderItem;
import com.arthursouza.swiftflowMVP.models.dto.OrderItemDTO.OrderItemCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.OrderItemDTO.OrderItemUpdateDTO;
import com.arthursouza.swiftflowMVP.services.Relations.OrderItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderItem")
@Validated
public class OrderItemController {
    
    @Autowired
    private OrderItemService orderItemService;


    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> findById(@Valid @PathVariable Long id){
        OrderItem orderItem = this.orderItemService.findById(id);

        return ResponseEntity.ok().body(orderItem);

    }

    //CRUD METHODS

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody OrderItemCreateDTO obj){
        OrderItem orderItem = this.orderItemService.fromDTO(obj);

        this.orderItemService.create(orderItem);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(orderItem.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody OrderItemUpdateDTO obj, @Valid @PathVariable Long id){
        OrderItem orderItem = this.orderItemService.fromDTO(obj);

        orderItem.setId(id);
        this.orderItemService.update(orderItem);

        return ResponseEntity.noContent().build();


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.orderItemService.delete(id);

        return ResponseEntity.noContent().build();


    }



}
