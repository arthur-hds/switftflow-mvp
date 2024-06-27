package com.arthursouza.swiftflowMVP.services.Relations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Relations.OrderItem;
import com.arthursouza.swiftflowMVP.models.dto.OrderItemDTO.OrderItemCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.OrderItemDTO.OrderItemUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.Relations.OrderItemRepository;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class OrderItemService {
    
    @Autowired
    private OrderItemRepository orderItemRepository;


    public OrderItem findById(Long id){
        Optional<OrderItem> orderItem = this.orderItemRepository.findById(id);

        return orderItem.orElseThrow(() -> new ObjectNotFoundException(
            "This ID doesn't match any Order. Id: " + id + " Type: "+ OrderItem.class.getName()));
        
    }


    public OrderItem findLastOrder(){
        OrderItem orderItem = this.orderItemRepository.findLastOrder();

        return orderItem;

    }


    //CRUD METHODS
    @Transactional
    public OrderItem create(OrderItem orderItem){
        orderItem.setId(null);
        orderItem = this.orderItemRepository.save(orderItem);

        return orderItem;
    }

    public OrderItem update(OrderItem orderItem){
        OrderItem newObj = findById(orderItem.getId());

        newObj.setOrders(orderItem.getOrders());
        newObj.setDisponibility(orderItem.getDisponibility());
        newObj.setOrderClient(orderItem.getOrderClient());
        
        return this.orderItemRepository.save(newObj);

    }

    public void delete(Long id){
        OrderItem obj = findById(id);

        try {
            
            this.orderItemRepository.delete(obj);
            
        } catch (Exception e) {
            throw new DataBindingViolationException("It's not possible to delete this Order. There are related entities");
        }


    }


    public OrderItem fromDTO(@Valid OrderItemCreateDTO obj){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(obj.getOrders_id());
        orderItem.setDisponibility(obj.getDisponibility());
        orderItem.setOrderClient(obj.getOrderClient());

        return orderItem;

    }


    public OrderItem fromDTO(@Valid OrderItemUpdateDTO obj){
        OrderItem orderItem = new OrderItem();

        orderItem.setId(obj.getId());
        orderItem.setOrders(obj.getOrders_id());
        orderItem.setDisponibility(obj.getDisponibility());
        orderItem.setOrderClient(obj.getOrderClient());

        return orderItem;

    }




}
