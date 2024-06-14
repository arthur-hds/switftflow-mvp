package com.arthursouza.swiftflowMVP.models.Relations;

import com.arthursouza.swiftflowMVP.models.Provider;

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
@Table(name = Orders.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Orders {
    
    public static final String TABLE_NAME = "Orders";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider_id;


    @Column(name = "status", nullable=false)
    @NotNull
    private boolean status;


    @Column(name = "total", nullable = false)
    @NotNull
    private Double total;



}
