package com.arthursouza.swiftflowMVP.controllers.Relations;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.arthursouza.swiftflowMVP.models.Relations.Orders;
import com.arthursouza.swiftflowMVP.models.dto.OrdersDTO.OrdersCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.OrdersDTO.OrdersUpdateDTO;
import com.arthursouza.swiftflowMVP.services.Relations.OrdersService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orders")
@Validated
public class OrdersController {
    
    @Autowired
    private OrdersService ordersService;


    @GetMapping("/{id}")
    public ResponseEntity<Orders> findById(@Valid @PathVariable Long id) {
        Orders orders = this.ordersService.findById(id);

        return ResponseEntity.ok().body(orders);
    }


    @GetMapping("/latest")
    public ResponseEntity<Orders> findLastOrder(){
        Orders orders = this.ordersService.findLastOrder();

        return ResponseEntity.ok().body(orders);
    }


    //CRUD METHODS

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody OrdersCreateDTO obj){
        Orders orders = this.ordersService.FromDTO(obj, false);

        this.ordersService.create(orders);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(orders.getId()).toUri();

        return ResponseEntity.created(uri).build();


    }


    @PutMapping("/{id}/{status}")
    public ResponseEntity<Void> update(@Valid @RequestBody OrdersUpdateDTO obj, @Valid @PathVariable Long id, @Valid @PathVariable boolean status){
        Orders newObj = this.ordersService.FromDTO(obj, status);

        newObj.setId(id);

        this.ordersService.udpate(newObj, status);

        return ResponseEntity.noContent().build();


    }


     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        this.ordersService.delete(id);

        return ResponseEntity.noContent().build();

    }


}
