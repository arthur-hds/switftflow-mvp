package com.arthursouza.swiftflowMVP.controllers;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arthursouza.swiftflowMVP.models.Team;
import com.arthursouza.swiftflowMVP.models.dto.TeamDTO.TeamCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.TeamDTO.TeamUpdateDTO;
import com.arthursouza.swiftflowMVP.services.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/team")
@Validated
public class TeamController {
    
    @Autowired
    private TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id){
        Team team = this.teamService.findbyId(id);

        return ResponseEntity.ok().body(team);
    }

    @GetMapping
    public ResponseEntity<List<Team>> findAllTeams(){
        List<Team> teams = this.teamService.findAllTeams();

        return ResponseEntity.ok().body(teams);

    }

    //CRUD METHODS

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody TeamCreateDTO obj){
        Team team = this.teamService.fromDTO(obj);

        this.teamService.create(team);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(team.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody TeamUpdateDTO obj, @PathVariable Long id){
        Team team = this.teamService.fromDTO(obj, id);
        team.setId(id);

        this.teamService.update(team);

        return ResponseEntity.noContent().build();
    } 

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.teamService.delete(id);

        return ResponseEntity.noContent().build();

    }


}
