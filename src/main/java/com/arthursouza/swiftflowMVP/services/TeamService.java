package com.arthursouza.swiftflowMVP.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthursouza.swiftflowMVP.models.Team;
import com.arthursouza.swiftflowMVP.models.dto.TeamDTO.TeamCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.TeamDTO.TeamUpdateDTO;
import com.arthursouza.swiftflowMVP.repositories.TeamRepository;

@Service
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;



    public Team findbyId(Long id){
        Optional<Team> team = this.teamRepository.findById(id);

        return team.orElseThrow(() -> new RuntimeException(
            "This ID doesn't match any team. Id: " + id + " Type: "+ Team.class.getName()));
    
    }


    //CRUD METHODS
    
    @Transactional
    public Team create(Team obj){
        obj.setId(null);    
        obj = this.teamRepository.save(obj);

        return obj;
    }

    
    @Transactional
    public Team update(Team obj){
        Team newTeam = findbyId(obj.getId());

        newTeam.setName(obj.getName());
        newTeam.setSponsor(obj.getSponsor());
        newTeam.setSport(obj.getSport());

        return this.teamRepository.save(newTeam);
        

    }

    @Transactional
    public void delete(Long id){
        Team obj = findbyId(id);

        try {
            this.teamRepository.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("It's not possible to delete this team. There are related entities");

        }

    }


    public Team fromDTO(TeamCreateDTO obj){

        Team team = new Team();
        team.setName(obj.getName());
        team.setSponsor(obj.getSponsor());
        team.setSport(obj.getSport());

        return team;

    }

    public Team fromDTO(TeamUpdateDTO obj){
        Team team = new Team();
        team.setId(obj.getId());;
        team.setSponsor(obj.getSponsor());
        team.setSport(obj.getSport());

        return team;


    }


}
