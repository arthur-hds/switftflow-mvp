package com.arthursouza.swiftflowMVP.models.dto.DisponibilityDTO;

import com.arthursouza.swiftflowMVP.models.Provider;
import com.arthursouza.swiftflowMVP.models.Shirt;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilityCreateDTO {
 

    private Provider provider_id;
    
    private Shirt shirt_id;

    @NotNull
    private Double price;

    @NotNull
    private Double sale;



}
