package com.arthursouza.swiftflowMVP.models.dto.OrderClientDTO;

import com.arthursouza.swiftflowMVP.models.Shirt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderClientUpdateDTO {
    
    private Long id;

    private Shirt shirt_id;

    @Size(min = 1, max = 20)
    @NotBlank
    private String size;
    

    @NotNull
    private Double additional;

  
    @NotNull
    private Double discount;

}
