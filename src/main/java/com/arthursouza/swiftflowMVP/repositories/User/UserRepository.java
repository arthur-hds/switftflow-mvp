package com.arthursouza.swiftflowMVP.repositories.User;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.arthursouza.swiftflowMVP.models.User.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    


}
