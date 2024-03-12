package com.arthursouza.swiftflowMVP.models.dto.ShirtDTO;

import com.arthursouza.swiftflowMVP.models.Team;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShirtCreateDTO {

    private Team team_id;

    @Size(min = 4, max = 20)
    @NotBlank
    private String type;

    @Size(min = 2, max = 8)
    @NotBlank
    private String season;

}
