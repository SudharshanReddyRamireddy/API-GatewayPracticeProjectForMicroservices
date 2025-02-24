package Cart.WishlistMicroservice.Cart.Wishlist.DTOs;

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
public class CartItem {
	
	private Long itemId;
	
	private Integer quentity;


}
