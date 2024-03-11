package com.arthursouza.swiftflowMVP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
    

    
}
