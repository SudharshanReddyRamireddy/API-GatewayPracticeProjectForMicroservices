package SpringPractice.UserFeignClient.DTOs;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse_DTO {
    
    
	 	@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Long id;
	    
	    private Long itemId;
	    
	    private Long userId;
	    private LocalDate dateOfOrder;
	    private Double totalCost;
	    private Long totalItems; 
		
}