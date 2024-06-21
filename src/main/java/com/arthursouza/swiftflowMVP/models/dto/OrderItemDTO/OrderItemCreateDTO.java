package com.arthursouza.swiftflowMVP.models.dto.OrderItemDTO;

import java.util.HashSet;
import java.util.Set;

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;
import com.arthursouza.swiftflowMVP.models.Relations.OrderClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreateDTO {
 
    private Set<Disponibility> disponibility = new HashSet<>();

    
    private OrderClient orderClient;


}
