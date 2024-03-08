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
@Table(name = Provider.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Provider {


    public static final String TABLE_NAME = "Provider";


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false, unique = true, length = 30)
    @Size(min = 3, max = 30)
    @NotBlank
    private String name;


    @Column(name = "delivery", nullable = false, length = 30)
    @Size(min = 5, max = 30)
    @NotBlank
    private String delivery;


    @Column(name = "minimum", nullable = false, length = 3)
    @Size(min = 1, max = 3)
    @NotBlank
    private Integer minimum;





}
