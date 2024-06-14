package com.arthursouza.swiftflowMVP.models.dto.OrdersDTO;

import com.arthursouza.swiftflowMVP.models.Provider;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCreateDTO {
    
   
    private Long id;

    
    private Provider provider_id;


    @NotNull
    private boolean status;


    @NotNull
    private Double total;

}
