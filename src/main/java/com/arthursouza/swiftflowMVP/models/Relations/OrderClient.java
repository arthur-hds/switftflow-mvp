package com.arthursouza.swiftflowMVP.models.Relations;

import com.arthursouza.swiftflowMVP.models.Client;
import com.arthursouza.swiftflowMVP.models.Shirt;

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
@Table(name = OrderClient.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderClient {
    
    public static final String TABLE_NAME = "OrderClient";


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "shirt_id", nullable = false)
    @ManyToOne
    private Shirt shirt_id;

    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne
    private Client client_id;

    @Column(name = "size", nullable = false, length = 20)
    @Size(min = 1, max = 20)
    @NotBlank
    private String size;

    @Column(name = "additional", nullable = true, length = 5)
    @NotNull
    private Double additional;
    
    @Column(name = "discount", nullable = true, length = 5)
    @NotNull
    private Double discount;




}
