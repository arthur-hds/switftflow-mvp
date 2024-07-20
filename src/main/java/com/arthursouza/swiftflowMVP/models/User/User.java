package com.arthursouza.swiftflowMVP.models.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arthursouza.swiftflowMVP.models.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = User.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    

    public static final String TABLE_NAME = "User";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;


    @Column(name = "username", unique = true)
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password")
    @NotBlank
    @Size(min = 8, max = 60)
    private String password;


    

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = Access.WRITE_ONLY)
    @CollectionTable(name = "user_profile")
    @Column(name = "profile", nullable = false)
    private Set<Integer> profiles = new HashSet<Integer>();


    //Returns all ProfileEnum objects (ADMIN, USER)
    public Set<ProfileEnum> getProfiles(){

        return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());

    }

}
