package com.arthursouza.swiftflowMVP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
    
    @Query(value = "SELECT * FROM provider", nativeQuery = true)
    public List<Provider> findAllProviders();
    
}
