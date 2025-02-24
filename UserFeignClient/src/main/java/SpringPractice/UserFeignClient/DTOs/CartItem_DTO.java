package SpringPractice.UserFeignClient.DTOs;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem_DTO {
	
	private Long itemId;
	
	private Integer quentity;
}
