package com.arthursouza.swiftflowMVP.models.dto.ClientDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientUpdateDTO {
    

    private Long id;

    @NotBlank
    @Size(min = 4, max = 30)
    private String name;


    @NotBlank
    @Size(min = 5, max = 30)
    private String type;

    
    @NotBlank
    @Size(min = 8, max = 15)
    private String number;


}
