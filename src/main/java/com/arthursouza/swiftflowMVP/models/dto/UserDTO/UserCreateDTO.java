package com.arthursouza.swiftflowMVP.models.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateDTO {
    
    @NotBlank    
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(min = 8, max = 60)
    private String password;
}
