package com.arthursouza.swiftflowMVP.repositories.Relations;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arthursouza.swiftflowMVP.models.Relations.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
    @Query(value = "SELECT * FROM order_item ORDER BY id DESC LIMIT 1", nativeQuery = true)
    OrderItem findLastOrder();

    
    @Query(value = "SELECT * FROM order_item WHERE order_item.orders_id = :id", nativeQuery = true)
    List<OrderItem> findOrderItemsById(@Param("id") Long id);

}   
