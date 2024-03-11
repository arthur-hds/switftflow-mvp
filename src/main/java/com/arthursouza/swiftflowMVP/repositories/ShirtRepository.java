package com.arthursouza.swiftflowMVP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Shirt;

@Repository
public interface ShirtRepository extends JpaRepository<Shirt, Long> {
    
}
