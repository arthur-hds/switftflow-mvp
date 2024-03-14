package com.arthursouza.swiftflowMVP.models.dto.ShirtDTO;

import com.arthursouza.swiftflowMVP.models.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShirtCreateDTO {

    @NotNull
    private Team team_id;

    @Size(min = 4, max = 20)
    @NotBlank
    private String type;

    @Size(min = 2, max = 8)
    @NotBlank
    private String season;

}
