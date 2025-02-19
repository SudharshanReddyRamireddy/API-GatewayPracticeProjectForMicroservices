package SpringPractice.UserFeignClient.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringPractice.UserFeignClient.DTOs.Cart_DTO;
import SpringPractice.UserFeignClient.feginClientAPIs.Cart_WishListMicroservice;
import SpringPractice.UserFeignClient.services.UserService;

@RestController
@RequestMapping("/users")
public class UserCartServiceController {
	
	@Autowired
	UserService userServices;
	
	@Autowired
	Cart_WishListMicroservice cartServices;
	
	
	
	//get cart items by user id
	@GetMapping("/cart/{userId}")
	public ResponseEntity<Cart_DTO> getCart(@PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			System.out.println("111");
			if(userServices.isUserExists(userId)) {
				return ResponseEntity.status(HttpStatus.OK).body(cartServices.getCartByCartId(userId));
			}else {
				throw new BadRequestException("ERROR : USER NOT FOUND ON ID : " + userId );
			}
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
		
	}
	
	
	
	
	// adding a item into respective user cart 
	@PutMapping("/addItem/{userId}/{itemId}")
	public ResponseEntity<String> addItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId) throws BadRequestException{
		
		try {
			if(userServices.isUserExists(userId)) {
				String response = cartServices.addItemToCart(userId, itemId);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}else {
				throw new BadRequestException("ERROR : USER NOT FOUND ON ID : " + userId);
			}
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	
	
	
	// clear user cart
	@PutMapping("/clear/{cartId}")
	public ResponseEntity<String> clearCart(@PathVariable("cartId") Long cartId) throws BadRequestException{
		
		try {
			System.out.println("1");
			return ResponseEntity.status(HttpStatus.OK).body(cartServices.clearCart(cartId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
}
