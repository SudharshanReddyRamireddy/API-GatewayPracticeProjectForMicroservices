package SpringPractice.UserFeignClient.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringPractice.UserFeignClient.DTOs.OrderResponse_DTO;
import SpringPractice.UserFeignClient.services.UserLocationsService;
import SpringPractice.UserFeignClient.services.UserOrderService;
import SpringPractice.UserFeignClient.services.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserOrderServiceController {
	
	@Autowired
	private UserOrderService userOrderService;
	
	@Autowired
	private UserService userServices;
	
	// method for user's cart check out by user id;
	@PostMapping("/cartCheckOut/{userId}/{locationId}")
	public ResponseEntity<OrderResponse_DTO> cartCheckOut(@PathVariable("userId") Long userId,@PathVariable("locationId") Long locationId) throws Exception{
		
		try {
			if(userServices.isUserExists(userId)) {
				OrderResponse_DTO order =  userOrderService.cartCheckOut(userId,locationId);
				
				userServices.sendOrderPacedMail(userId, userId);
				return ResponseEntity.status(HttpStatus.OK).body(order);
			}else {
				throw new BadRequestException("ERROR : USER NOT EXIST WITH ID " + userId);
			}
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
		
	}
	
	

}
