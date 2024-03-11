package com.arthursouza.swiftflowMVP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
    
    
}
