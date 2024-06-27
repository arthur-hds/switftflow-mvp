package com.arthursouza.swiftflowMVP.models.dto.OrderItemDTO;

import java.util.HashSet;
import java.util.Set;

import com.arthursouza.swiftflowMVP.models.Relations.Disponibility;
import com.arthursouza.swiftflowMVP.models.Relations.OrderClient;
import com.arthursouza.swiftflowMVP.models.Relations.Orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreateDTO {
 
    private Disponibility disponibility_id;

    
    private OrderClient orderClient;

    private Orders orders_id;


}
