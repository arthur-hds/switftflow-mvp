package com.arthursouza.swiftflowMVP.controllers.User;

import java.net.URI;

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

import com.arthursouza.swiftflowMVP.models.User.User;
import com.arthursouza.swiftflowMVP.models.dto.UserDTO.UserCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.UserDTO.UserUpdateDTO;
import com.arthursouza.swiftflowMVP.services.User.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = this.userService.findById(id);

        return ResponseEntity.ok().body(user);

        
    }


    //CRUD METHODS

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody @Valid UserCreateDTO obj){
        User user = this.userService.fromDTO(obj);

        this.userService.create(user);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateDTO obj, @PathVariable Long id){
        User user = this.userService.fromDTO(obj);

        user.setId(id);
        this.userService.update(user);


        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.userService.delete(id);

        return ResponseEntity.noContent().build();


    }


}
