package com.arthursouza.swiftflowMVP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Shirt;

@Repository
public interface ShirtRepository extends JpaRepository<Shirt, Long> {
    
    @Query(value = "SELECT * FROM shirt", nativeQuery = true)
    public List<Shirt> findAllShirts();

}
