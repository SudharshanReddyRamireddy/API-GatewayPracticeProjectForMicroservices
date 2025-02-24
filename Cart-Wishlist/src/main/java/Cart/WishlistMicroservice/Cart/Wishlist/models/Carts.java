package Cart.WishlistMicroservice.Cart.Wishlist.models;


import java.util.List;

import Cart.WishlistMicroservice.Cart.Wishlist.DTOs.CartItem;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Carts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Carts {
	
	@Id
	private Long id;
	
	private Double cartPrice;
	
	
	@ElementCollection
	private List<CartItem> cartItems;


	
	
}
