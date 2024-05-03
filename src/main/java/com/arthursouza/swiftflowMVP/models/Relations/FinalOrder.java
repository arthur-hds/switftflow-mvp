package com.arthursouza.swiftflowMVP.models.Relations;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = FinalOrder.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalOrder {
    
    public static final String TABLE_NAME = "FinalOrder";


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinTable(
        name = "order_disponibility",
        joinColumns = @JoinColumn(name="Order_id"),
        inverseJoinColumns = @JoinColumn(name="Disponibilty_id")
    )
    private Set<Disponibility> disponibility = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "orderClient_id", nullable = false)
    private OrderClient orderClient;

    @Column(name = "cost", nullable = false)
    @NotNull
    private Double cost;

    @Column(name = "profit", nullable = false)
    @NotNull
    private Double profit;

    @Column(name = "total", nullable = false)
    @NotNull
    private Double total;



}
