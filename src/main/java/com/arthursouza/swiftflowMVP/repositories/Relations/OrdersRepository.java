package com.arthursouza.swiftflowMVP.repositories.Relations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Relations.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
}
