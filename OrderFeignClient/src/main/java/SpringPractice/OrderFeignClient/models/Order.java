package SpringPractice.OrderFeignClient.models;
import java.time.LocalDate;

import SpringPractice.OrderFeignClient.DTOs.OrderItemDTO;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.*;
import lombok.Setter;

@Entity
@Table(name = "orders") // Rename the table to avoid conflicts
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ElementCollection
    private List<OrderItemDTO> items;
    
    private Long userId;
    private LocalDate dateOfOrder;
    private Double totalCost;
    private Long totalItems;
    private String TransationId;
    private Long locationId;
    

}
