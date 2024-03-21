package com.arthursouza.swiftflowMVP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    @Query(value = "SELECT * FROM client", nativeQuery = true)
    List<Client> findAllClients();

    
}
