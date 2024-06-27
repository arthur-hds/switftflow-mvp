package com.arthursouza.swiftflowMVP.models.Relations;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = OrderItem.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    
    public static final String TABLE_NAME = "OrderItem";


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;


    @ManyToOne
    @JoinColumn(name = "disponibility_id", nullable = false)
    private Disponibility disponibility;

    @OneToOne
    @JoinColumn(name = "orderClient_id", nullable = false)
    private OrderClient orderClient;




}
