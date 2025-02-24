package SpringPractice.UserFeignClient.DTOs;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist_DTO {
	
	private Long wishListId;
	
	private Set<Long> itemsList;


}
