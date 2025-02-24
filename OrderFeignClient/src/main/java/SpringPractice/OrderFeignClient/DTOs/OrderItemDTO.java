package SpringPractice.OrderFeignClient.DTOs;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
	
	private Long itemId;
	
	private Integer quentity;

}
