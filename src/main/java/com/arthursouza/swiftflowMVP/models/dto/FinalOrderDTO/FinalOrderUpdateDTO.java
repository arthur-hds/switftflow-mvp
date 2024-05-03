package com.arthursouza.swiftflowMVP.models.dto.FinalOrderDTO;

import java.util.HashSet;
import java.util.Set;

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;
import com.arthursouza.swiftflowMVP.models.Relations.OrderClient;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalOrderUpdateDTO {
    

    private Long id;


    private Set<Disponibility> disponibility = new HashSet<>();

    private OrderClient orderClient;


    @NotNull
    private Double cost;


    @NotNull
    private Double profit;


    @NotNull
    private Double total;



}
