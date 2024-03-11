package com.arthursouza.swiftflowMVP.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = Team.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team {
    
    public static final String TABLE_NAME = "Team";


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 5, max = 30)
    @NotBlank
    private String name;


    @Column(name = "sponsor", nullable = false, unique = false, length = 30)
    @Size(min = 2, max = 30)
    @NotBlank
    private String sponsor;


    @Column(name = "sport", nullable = false, unique = false, length = 20)
    @Size(min = 4, max = 20)
    @NotBlank
    private String sport;



}
