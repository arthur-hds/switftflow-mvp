package com.arthursouza.swiftflowMVP.repositories.Relations;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arthursouza.swiftflowMVP.models.Relations.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
    @Query(value = "SELECT * FROM order_item ORDER BY id DESC LIMIT 1", nativeQuery = true)
    OrderItem findLastOrder();

}
