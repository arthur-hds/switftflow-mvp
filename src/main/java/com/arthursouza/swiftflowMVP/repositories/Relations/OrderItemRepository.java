package com.arthursouza.swiftflowMVP.repositories.Relations;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthursouza.swiftflowMVP.models.Relations.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
 
    

}
