package Cart.WishlistMicroservice.Cart.Wishlist.models;


import java.util.List;

import Cart.WishlistMicroservice.Cart.Wishlist.DTOs.CartItem;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carts")
public class Carts {
	
	@Id
	private Long id;
	
	private Double cartPrice;
	
	
	@ElementCollection
	private List<CartItem> cartItems;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<CartItem> getCartItems() {
		return cartItems;
	}

	
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}


	public Double getCartPrice() {
		return cartPrice;
	}


	public void setCartPrice(Double cartPrice) {
		this.cartPrice = cartPrice;
	}
	
}
