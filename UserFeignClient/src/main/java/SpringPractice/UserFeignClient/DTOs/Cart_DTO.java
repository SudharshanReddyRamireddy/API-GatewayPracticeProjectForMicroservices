package SpringPractice.UserFeignClient.DTOs;

import java.util.List;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Cart_DTO {

	private Long id;
	
	private Double cartPrice;

	
	@ElementCollection
	private List<CartItem_DTO> cartItems;

	

}
