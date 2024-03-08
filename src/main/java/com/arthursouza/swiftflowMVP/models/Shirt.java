package com.arthursouza.swiftflowMVP.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Shirt.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Shirt {
    
    
    public static final String TABLE_NAME = "Shirt";


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id", nullable = false)
    @ManyToOne
    private Team team_id;


    @Column(name = "type", nullable = false, length = 30)
    @Size(min = 4, max = 20)
    @NotBlank
    private String type;

    @Column(name = "season", nullable = false, length = 8)
    @Size(min = 2, max = 8)
    @NotBlank
    private String season;


}
