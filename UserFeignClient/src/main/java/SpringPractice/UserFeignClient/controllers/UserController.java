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

import SpringPractice.UserFeignClient.DTOs.OrderResponse_DTO;
import SpringPractice.UserFeignClient.feginClientAPIs.OrderMicroservice;
import SpringPractice.UserFeignClient.models.User;
import SpringPractice.UserFeignClient.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderMicroservice orderFeginClientAPIs;
	
	// saving a new user
	@PostMapping("/user")
	public ResponseEntity<Object> saveUser(@RequestBody User user){
		
		try {
			User responseUser = userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.OK).body(responseUser);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	// fetching order list by user id
	@GetMapping("/OrdersByUserId/{userId}")
	public ResponseEntity<List<OrderResponse_DTO>> getOrdersByUserId(@PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderFeginClientAPIs.getOrdersByUserId(userId));
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
		
	}
	
	
	//saving a order on specific user id
	@PostMapping("/OrderOnUser/{userId}")
	public ResponseEntity<OrderResponse_DTO> saveOrder(@RequestBody OrderResponse_DTO orderDetails, @PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			if(userService.isUserExists(userId)) {
				orderDetails.setId(null);
				orderDetails.setUserId(userId);
				OrderResponse_DTO order = orderFeginClientAPIs.createOrder(orderDetails);
				return ResponseEntity.status(HttpStatus.OK).body(order);
			}else {
				throw new BadRequestException("ERROR : USER NOT FOUND WITH GIVEN ID " + userId);
			}
			
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	

	
}
