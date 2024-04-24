package com.arthursouza.swiftflowMVP.repositories.Relations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Relations.OrderClient;

@Repository
public interface OrderClientRepository extends JpaRepository<OrderClient, Long> {
    
    @Query(value = "SELECT * FROM order_client", nativeQuery = true)
    List<OrderClient> findAllOrderClient();

    @Query(value = "SELECT * FROM order_client d where d.client_id = :id", nativeQuery = true)
    List<OrderClient> findAllClientsOrderClient(@Param("id") Long id);

}
