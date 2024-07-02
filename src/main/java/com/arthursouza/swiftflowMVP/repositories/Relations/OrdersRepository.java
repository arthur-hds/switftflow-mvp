package com.arthursouza.swiftflowMVP.repositories.Relations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.arthursouza.swiftflowMVP.models.Relations.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
    @Query(value = "SELECT * FROM orders ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Orders findLastOrder();


    @Query(value = "SELECT * FROM orders", nativeQuery = true)
    List<Orders> findAllOrders();

}
