package com.arthursouza.swiftflowMVP.models.dto.OrderItemDTO;

import java.util.HashSet;
import java.util.Set;

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;
import com.arthursouza.swiftflowMVP.models.Relations.OrderClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemUpdateDTO {
    

    private Long id;


    private Set<Disponibility> disponibility = new HashSet<>();

    private OrderClient orderClient;




}
