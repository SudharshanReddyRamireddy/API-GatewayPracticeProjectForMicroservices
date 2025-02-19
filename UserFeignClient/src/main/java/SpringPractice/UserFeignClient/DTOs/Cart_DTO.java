package SpringPractice.UserFeignClient.DTOs;

import java.util.List;

import jakarta.persistence.ElementCollection;

public class Cart_DTO {

	private Long id;
	
	private Double cartPrice;

	
	@ElementCollection
	private List<CartItem_DTO> cartItems;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<CartItem_DTO> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItem_DTO> cartItems) {
		this.cartItems = cartItems;
	}


	public Double getCartPrice() {
		return cartPrice;
	}


	public void setCartPrice(Double cartPrice) {
		this.cartPrice = cartPrice;
	}

	

}
