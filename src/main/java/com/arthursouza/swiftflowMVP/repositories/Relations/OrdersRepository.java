package com.arthursouza.swiftflowMVP.repositories.Relations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.arthursouza.swiftflowMVP.models.Relations.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
    @Query(value = "SELECT * FROM orders ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Orders findLastOrder();

}
