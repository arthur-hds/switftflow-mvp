package com.arthursouza.swiftflowMVP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
    
    @Query(value = "SELECT * from team", nativeQuery = true)
    public List<Team> findAllTeams();

}
