package com.arthursouza.swiftflowMVP.services.User;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arthursouza.swiftflowMVP.models.User.User;
import com.arthursouza.swiftflowMVP.repositories.User.UserRepository;
import com.arthursouza.swiftflowMVP.security.UserSpringSecurity;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    //Give authorities to the following User
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not founded: "+ username);
        }


        UserSpringSecurity userAuthenticated = new UserSpringSecurity(user.getId(), user.getUsername(), user.getPassword(), user.getProfiles());
        

        return userAuthenticated;
    }
}
