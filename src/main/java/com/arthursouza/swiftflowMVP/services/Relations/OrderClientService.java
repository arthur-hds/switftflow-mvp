package com.arthursouza.swiftflowMVP.services.Relations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthursouza.swiftflowMVP.models.Relations.OrderClient;
import com.arthursouza.swiftflowMVP.models.dto.OrderClientDTO.OrderClientCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.OrderClientDTO.OrderClientUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.Relations.OrderClientRepository;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OrderClientService {
 
    @Autowired
    private OrderClientRepository orderClientRepository;


    public OrderClient findById(Long id){
        Optional<OrderClient> orderClient = this.orderClientRepository.findById(id);

        return orderClient.orElseThrow( () -> new ObjectNotFoundException(
            "This ID doesn't match any OrderClient. Id: " + id + " Type: "+ OrderClient.class.getName()));

    }


    public List<OrderClient> findAllOrderClient(){
        List<OrderClient> orderClients = this.orderClientRepository.findAllOrderClient();

        return orderClients;


    }



    //CRUD METHODS 
    @Transactional
    public OrderClient create(OrderClient orderClient){
        orderClient.setId(null);
        orderClient = this.orderClientRepository.save(orderClient);
        
        return orderClient;

    }

    @Transactional
    public OrderClient update(OrderClient orderClient){
        OrderClient newOrderClient = findById(orderClient.getId());

        newOrderClient.setAdditional(orderClient.getAdditional());
        newOrderClient.setClient_id(orderClient.getClient_id());
        newOrderClient.setDiscount(orderClient.getDiscount());
        newOrderClient.setShirt_id(orderClient.getShirt_id());
        newOrderClient.setSize(orderClient.getSize());

        return this.orderClientRepository.save(newOrderClient);

    }

    @Transactional
    public void delete(Long id){    
        OrderClient orderClient = findById(id);

        try {
            this.orderClientRepository.delete(orderClient);
        } catch (Exception e) {
            throw new DataBindingViolationException("It's not possible to delete this OrderClient. There are related entities");
        }


    }


    public OrderClient fromDTO(@Valid OrderClientCreateDTO obj){
        OrderClient orderClient = new OrderClient();
        
        orderClient.setAdditional(obj.getAdditional());
        orderClient.setClient_id(obj.getClient_id());
        orderClient.setDiscount(obj.getDiscount());
        orderClient.setShirt_id(obj.getShirt_id());
        orderClient.setSize(obj.getSize());

        return orderClient;


    }

    public OrderClient fromDTO(@Valid OrderClientUpdateDTO obj, Long id){
        OrderClient orderClient = new OrderClient();
        
        orderClient.setId(id);
        orderClient.setAdditional(obj.getAdditional());
        orderClient.setDiscount(obj.getDiscount());
        orderClient.setShirt_id(obj.getShirt_id());
        orderClient.setClient_id(findById(id).getClient_id());
        orderClient.setSize(obj.getSize());

        return orderClient;

    }


}
