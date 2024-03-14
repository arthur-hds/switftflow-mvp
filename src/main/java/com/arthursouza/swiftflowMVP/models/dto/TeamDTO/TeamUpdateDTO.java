package com.arthursouza.swiftflowMVP.models.dto.TeamDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamUpdateDTO {

    private Long id;

    @Size(min = 2, max = 30)
    @NotBlank
    private String sponsor;

    @Size(min = 4, max = 20)
    @NotBlank
    private String sport;


}
