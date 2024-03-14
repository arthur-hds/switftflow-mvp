package com.arthursouza.swiftflowMVP.models.dto.ProviderDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProviderUpdateDTO {
    

    private Long id;

    @Size(min = 3, max = 30)
    @NotBlank
    private String name;

    @Size(min = 5, max = 30)
    @NotBlank
    private String delivery;

    @Min(value = 1, message = "Minimum value must be higher or equal than 1")
    @NotNull
    private Integer minimum;


}
