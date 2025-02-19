package SpringPractice.UserFeignClient.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringPractice.UserFeignClient.DTOs.Cart_DTO;
import SpringPractice.UserFeignClient.DTOs.OrderResponse_DTO;
import SpringPractice.UserFeignClient.feginClientAPIs.Cart_WishListMicroservice;
import SpringPractice.UserFeignClient.feginClientAPIs.OrderMicroservice;
import SpringPractice.UserFeignClient.models.User;
import SpringPractice.UserFeignClient.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderMicroservice orderFeginClientAPIs;
	
	@Autowired
	private Cart_WishListMicroservice cart_WishListServices;
	
	
	
	
	// saving a new user
	@PostMapping("/user")
	@Transactional
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		
		try {
			if(userService.isMailIdExist(user.getEmailId())) {
				throw new BadRequestException("ERROR : MAILD ID ALREADY EXIST.");
			}else if (userService.isMobileNoExist(user.getMobileNo())) {
				throw new BadRequestException("ERROR : Mobile No ALREADY EXIST");
			}
			User responseUser = userService.saveUser(user);
			
			Cart_DTO cart = new Cart_DTO();
			cart.setId(responseUser.getId());
			cart_WishListServices.createCart(cart);
			return ResponseEntity.status(HttpStatus.OK).body(responseUser);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	
	//fetching all users
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsersList() throws BadRequestException {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUserList());
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	
	
	
	
	//fetching user details by user id
	@GetMapping("/userById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			
			User user = userService.getUserById(userId);
			if(user == null) {
				throw new BadRequestException("ERROR : USER NOT FOUND ON GIVEN ID");
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
			}
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	
	
	
	// fetching order list by user id
	@GetMapping("/OrdersByUserId/{userId}")
	public ResponseEntity<List<OrderResponse_DTO>> getOrdersByUserId(@PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			if(userService.isUserExists(userId)) {
				return ResponseEntity.status(HttpStatus.OK).body(orderFeginClientAPIs.getOrdersByUserId(userId));
			}else {
				throw new BadRequestException("ERROR : USER NOT FOUND ON GIVEN ID");
			}
			
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	
	
	
	
	
	//saving a order on specific user id
	@PostMapping("/OrderOnUser/{userId}")
	@Transactional
	public ResponseEntity<OrderResponse_DTO> saveOrder(@RequestBody OrderResponse_DTO orderDetails, @PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			if(userService.isUserExists(userId)) {
				orderDetails.setId(null);
				orderDetails.setUserId(userId);
				OrderResponse_DTO order = orderFeginClientAPIs.createOrder(orderDetails);
				userService.sendOrderPacedMail(userId, userId);
				return ResponseEntity.status(HttpStatus.OK).body(order);
			}else {
				throw new BadRequestException("ERROR : USER NOT FOUND WITH GIVEN ID " + userId);
			}
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
}
