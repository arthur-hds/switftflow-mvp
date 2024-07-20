package com.arthursouza.swiftflowMVP.services.User;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.User.User;
import com.arthursouza.swiftflowMVP.models.dto.UserDTO.UserCreateDTO;
import com.arthursouza.swiftflowMVP.models.dto.UserDTO.UserUpdateDTO;
import com.arthursouza.swiftflowMVP.models.enums.ProfileEnum;
import com.arthursouza.swiftflowMVP.repositories.User.UserRepository;
import com.arthursouza.swiftflowMVP.security.UserSpringSecurity;
import com.arthursouza.swiftflowMVP.services.exceptions.AuthorizationException;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findById(Long id){
        UserSpringSecurity userSpringSecurity = authenticated();
        if(!Objects.nonNull(userSpringSecurity)){
            throw new AuthorizationException("User Denied");
        }

        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException(
            "This ID doesn't match any user. Id: " + id + " Type: "+ User.class.getName()));
    }


    //CRUD METHODS

    public User create(User user){
        user.setId(null);
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        user = this.userRepository.save(user);

        return user;
    }


    public User update(User user){
        User newUser = this.findById(user.getId());

        newUser.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
      

        return this.userRepository.save(newUser);

    }


    public void delete(Long id){
        User user = findById(id);
        

        try{
            this.userRepository.delete(user);
        }catch(Exception ex){
            throw new DataBindingViolationException("It's not possible to delete this user. There are related entities");
        }


    }


    public User fromDTO(@Valid UserCreateDTO obj){
        User user = new User();

        user.setPassword(obj.getPassword());
        user.setUsername(obj.getUsername());

        return user;
    }

    public User fromDTO(@Valid UserUpdateDTO obj){
        User user = new User();

        user.setId(obj.getId());
        user.setPassword(obj.getPassword());

        return user;
    }


    public static UserSpringSecurity authenticated(){

        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }

    }

}
