package Cart.WishlistMicroservice.Cart.Wishlist.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cart.WishlistMicroservice.Cart.Wishlist.models.Carts;
import Cart.WishlistMicroservice.Cart.Wishlist.services.CartServices;

@RestController
@RequestMapping("/Cart")
public class CartServiceController {

	@Autowired
	private CartServices cartServices;
	
	
	//creating cart for user, here user Id == cart id; 
	@PostMapping("/cart")
	public void createCart(@RequestBody Carts cart) {
		try {
			cartServices.createCart(cart);
		} catch (Exception e) {
			throw new InternalError("ERROR : WHILE CREATEING CART : " + e.getMessage());
		}
	}
	
	
	//adding item to cart
	@PutMapping("/addItem/{cartId}/{itemId}")
	public ResponseEntity<String> addItemToCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) throws BadRequestException{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cartServices.addItemToCart(cartId, itemId));
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	
	
	//fetching cart details by cart id
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<Carts> getCartByCartId(@PathVariable("cartId") Long cartId) throws BadRequestException {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cartServices.getCartByCartId(cartId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : "+ e.getMessage());
		}
	}
	
	
	
	@PutMapping("/removeItem/{cartId}/{itemId}")
	public ResponseEntity<String> removeItemFromCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) throws BadRequestException{
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cartServices.removeItem(cartId, itemId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
		
	}
	
	
	@PutMapping("/clearCart/{cartId}")
	public ResponseEntity<String> clearCart(@PathVariable("cartId") Long cartId) throws BadRequestException{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cartServices.clearCart(cartId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	
	
	
}
