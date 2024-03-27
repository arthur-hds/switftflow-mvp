package com.arthursouza.swiftflowMVP.repositories.Relations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;

@Repository
public interface DisponibilityRepository extends JpaRepository<Disponibility, Long>{
    


}
