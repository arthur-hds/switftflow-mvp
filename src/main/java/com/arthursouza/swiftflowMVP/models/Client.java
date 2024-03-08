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
@Table(name = Client.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    



    public static final String TABLE_NAME = "Client";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;



    @Column(name = "name", nullable = false, length = 30, unique = true)
    @NotBlank
    @Size(min = 4, max = 30)
    private String name;


    @Column(name = "type", nullable = false, length = 30)
    @NotBlank
    @Size(min = 5, max = 30)
    private String type;


    @Column(name = "number", nullable = true, length = 15)
    @NotBlank
    @Size(min = 8, max = 15)
    private String number;

}
