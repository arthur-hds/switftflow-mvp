package com.arthursouza.swiftflowMVP.models.dto.ShirtDTO;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShirtUpdateDTO {
    

    private Long id;

    @Size(min = 4, max = 20)
    @NotBlank
    private String type;

    @Size(min = 2, max = 8)
    @NotBlank
    private String season;

}
