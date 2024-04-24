package com.arthursouza.swiftflowMVP.repositories.Relations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;

@Repository
public interface DisponibilityRepository extends JpaRepository<Disponibility, Long>{
    
    @Query(value = "SELECT * FROM disponibility", nativeQuery = true)
    List<Disponibility> findAllDisponibilities();

    @Query(value = "SELECT * FROM disponibility d where d.provider_id = :id", nativeQuery = true)
    List<Disponibility> findAllProvidersDisponibilities(@Param("id") Long id);

}
