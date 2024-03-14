package com.arthursouza.swiftflowMVP.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Disponibility.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Disponibility {
    

    public static final String TABLE_NAME = "Disponibility";

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "provider_id", nullable = false)
    @ManyToOne
    private Provider provider_id;
    

    @JoinColumn(name = "shirt_id", nullable = false)
    @ManyToOne
    private Shirt shirt_id;

    @Column(name = "price", nullable = false, length = 5)
    @Size(min = 2, max = 5)
    @NotNull
    private Double price;

    @Column(name = "sale", nullable = false, length = 5)
    @Size(min = 2, max = 5)
    @NotNull
    private Double sale;


}
