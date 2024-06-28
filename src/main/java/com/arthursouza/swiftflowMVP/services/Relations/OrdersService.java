package com.arthursouza.swiftflowMVP.services.Relations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Relations.Orders;
import com.arthursouza.swiftflowMVP.models.dto.OrdersDTO.OrdersCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.OrdersDTO.OrdersUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.Relations.OrdersRepository;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class OrdersService {
    
    @Autowired
    private OrdersRepository ordersRepository;

    
    public Orders findById(Long id){
        Optional<Orders> orders = this.ordersRepository.findById(id);
    
        return orders.orElseThrow(() -> new ObjectNotFoundException(
            "This ID doesn't match any Orders. Id: " + id + " Type: "+ Orders.class.getName()));


    }
    

    public Orders findLastOrder(){
        Orders orders = this.ordersRepository.findLastOrder();

        return orders;


    }



    //CRUD METHODS

    @Transactional
    public Orders create(Orders orders){
        orders.setId(null);
        orders = this.ordersRepository.save(orders);

        return orders;

    }

    @Transactional
    public Orders udpate(Orders orders, boolean status){



        Orders newObj = findById(orders.getId());
        newObj.setId(orders.getId());
        newObj.setStatus(status); //! .getStatus() not working
        newObj.setTotal(orders.getTotal());

        return this.ordersRepository.save(newObj);
        


    }


    @Transactional
    public void delete(Long id){
        Orders obj = findById(id);

        try {
            this.ordersRepository.delete(obj);
        } catch (Exception e) {
            throw new DataBindingViolationException("It's not possible to delete this Order. There are related entities");
        }


    }


    public Orders FromDTO(OrdersCreateDTO orders, boolean status){
        Orders obj = new Orders();

        obj.setId(orders.getId());
        obj.setProvider_id(orders.getProvider_id());
        obj.setStatus(status);
        obj.setTotal(orders.getTotal());

        return obj;

    }

    public Orders FromDTO(OrdersUpdateDTO orders, boolean status){
        Orders obj = new Orders();

        obj.setId(orders.getId());
        obj.setStatus(status);
        obj.setTotal(orders.getTotal());

        return obj;

    }


}
