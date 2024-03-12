package com.arthursouza.swiftflowMVP.models.dto.ProviderDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderCreateDTO {
    
    @Size(min = 3, max = 30)
    @NotBlank
    private String name;


    @Size(min = 5, max = 30)
    @NotBlank
    private String delivery;


    @Size(min = 1, max = 3)
    @NotBlank
    private Integer minimum;

}
