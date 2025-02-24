package SpringPractice.UserFeignClient.feginClientAPIs;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import SpringPractice.UserFeignClient.DTOs.Cart_DTO;
import SpringPractice.UserFeignClient.DTOs.Wishlist_DTO;


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
	
	
	@PostMapping("Cart/createWishList/{userId}")
	public String createWishList(@PathVariable("userId") Long userId);
	
	
	@PostMapping("/Cart/wishList/addItem/{userId}/{itemId}")
	public String addItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId);
	
	
	@PostMapping("/Cart/wishList/remove/{userId}/{itemId}")
	public ResponseEntity<String> removeItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId);
	
	
	@GetMapping("/Cart/wishList/{userId}")
	public ResponseEntity<Wishlist_DTO>getWishList(@PathVariable("userId") Long userId);
	
	
	@PutMapping("Cart/removeItem/{cartId}/{itemId}")
	public ResponseEntity<String> removeItemFromCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId);
	
}
