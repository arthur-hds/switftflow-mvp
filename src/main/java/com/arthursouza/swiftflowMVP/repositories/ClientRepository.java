package com.arthursouza.swiftflowMVP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    

    
}
