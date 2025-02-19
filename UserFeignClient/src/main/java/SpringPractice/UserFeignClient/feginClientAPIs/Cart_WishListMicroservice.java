package SpringPractice.UserFeignClient.feginClientAPIs;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import SpringPractice.UserFeignClient.DTOs.Cart_DTO;


@FeignClient(name = "CART-SERVICES")
public interface Cart_WishListMicroservice {
	
	@PostMapping("/Cart/cart")
	public void createCart(@RequestBody Cart_DTO cart);
	
	
	@PutMapping("/Cart/addItem/{cartId}/{itemId}")
	public String addItemToCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId);
	
	
	@PutMapping("Cart/clearCart/{cartId}")
	public String clearCart(@PathVariable("cartId") Long cartId);
	
	
	@GetMapping("/Cart/cart/{cartId}")
	public Cart_DTO getCartByCartId(@PathVariable("cartId") Long cartId);
	
}
