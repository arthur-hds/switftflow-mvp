package com.arthursouza.swiftflowMVP.services.Relations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.Relations.FinalOrder;
import com.arthursouza.swiftflowMVP.models.dto.FinalOrderDTO.FinalOrderCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.FinalOrderDTO.FinalOrderUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.Relations.FinalOrderRepository;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class FinalOrderService {
    
    @Autowired
    private FinalOrderRepository finalOrderRepository;


    public FinalOrder findById(Long id){
        Optional<FinalOrder> finalOrder = this.finalOrderRepository.findById(id);

        return finalOrder.orElseThrow(() -> new ObjectNotFoundException(
            "This ID doesn't match any Order. Id: " + id + " Type: "+ FinalOrder.class.getName()));
        
    }


    //CRUD METHODS
    @Transactional
    public FinalOrder create(FinalOrder finalOrder){
        finalOrder.setId(null);
        finalOrder = this.finalOrderRepository.save(finalOrder);

        return finalOrder;
    }

    public FinalOrder update(FinalOrder finalOrder){
        FinalOrder newObj = findById(finalOrder.getId());

        
        newObj.setDisponibility(finalOrder.getDisponibility());
        newObj.setCost(finalOrder.getCost());
        newObj.setOrderClient(finalOrder.getOrderClient());
        newObj.setProfit(finalOrder.getProfit());
        newObj.setTotal(finalOrder.getTotal());
        
        return this.finalOrderRepository.save(newObj);

    }

    public void delete(Long id){
        FinalOrder obj = findById(id);

        try {
            
            this.finalOrderRepository.delete(obj);
            
        } catch (Exception e) {
            throw new DataBindingViolationException("It's not possible to delete this Order. There are related entities");
        }


    }


    public FinalOrder fromDTO(@Valid FinalOrderCreateDTO obj){
        FinalOrder finalOrder = new FinalOrder();
        finalOrder.setProfit(obj.getProfit());
        finalOrder.setCost(obj.getCost());
        finalOrder.setDisponibility(obj.getDisponibility());
        finalOrder.setOrderClient(obj.getOrderClient());
        finalOrder.setTotal(obj.getTotal());

        return finalOrder;

    }


    public FinalOrder fromDTO(@Valid FinalOrderUpdateDTO obj){
        FinalOrder finalOrder = new FinalOrder();

        finalOrder.setId(obj.getId());
        finalOrder.setProfit(obj.getProfit());
        finalOrder.setCost(obj.getCost());
        finalOrder.setDisponibility(obj.getDisponibility());
        finalOrder.setOrderClient(obj.getOrderClient());
        finalOrder.setTotal(obj.getTotal());

        return finalOrder;

    }




}
