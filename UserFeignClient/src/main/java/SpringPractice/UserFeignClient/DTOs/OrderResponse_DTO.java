package SpringPractice.UserFeignClient.DTOs;

import java.time.LocalDate;
import java.util.List;

import SpringPractice.UserFeignClient.models.Location;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse_DTO {
	
	    private Long id;
	    
	    @ElementCollection
	    private List<CartItem_DTO> items;
	    
	    private Long userId;
	    private LocalDate dateOfOrder;
	    private Double totalCost;
	    private Long totalItems;
	    private Long locationId;
		
}