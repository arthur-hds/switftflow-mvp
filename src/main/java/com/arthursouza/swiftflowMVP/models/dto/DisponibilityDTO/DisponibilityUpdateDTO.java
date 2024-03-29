package com.arthursouza.swiftflowMVP.models.dto.DisponibilityDTO;

import com.arthursouza.swiftflowMVP.models.Provider;
import com.arthursouza.swiftflowMVP.models.Shirt;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilityUpdateDTO {
    

    private Long id;

    private Shirt shirt_id;


    @NotNull
    private Double price;

    @NotNull
    private Double sale;


}
