package com.arthursouza.swiftflowMVP.models.Relations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Order.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    public static final String TABLE_NAME = "Order";


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinColumn(name = "disponibility_id", nullable = false)
    private Disponibility disponibility;

    @OneToOne
    private OrderClient orderClient;

    

}
