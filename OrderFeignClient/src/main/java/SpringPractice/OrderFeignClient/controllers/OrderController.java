package SpringPractice.OrderFeignClient.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import SpringPractice.OrderFeignClient.FeignClientAPIs.UserMicroservice;
import SpringPractice.OrderFeignClient.models.Order;
import SpringPractice.OrderFeignClient.services.OrderService;

/**
 * Controller for handling order-related operations.
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
@Tag(name = "Order Controller", description = "APIs for managing orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserMicroservice userMicroservice;
	
	
	/**
	 * Adds a new order.
	 * 
	 * @param order The order details to be saved.
	 * @return The saved order.
	 * @throws BadRequestException If the item does not exist.
	 */
	@Operation(summary = "Add new order", description = "Creates a new order if the item exists.")
	@PostMapping("/order")
	@Transactional
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) throws BadRequestException{
		try {
			//Boolean isItemExist = feignClientItemServices.isItemExist(order.getItemId());
//			if(userMicroservice.isUserExist(order.getUserId()).getBody()) {
//				return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(order));
//			}else {
//				throw new NotFoundException("ERROR : INVALID USER ID : USER ID NOT FOUND ON ID : " + order.getUserId() );
//			}
			
			return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(order));
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	
	/**
	 * Retrieves all orders for a given user ID.
	 * 
	 * @param userId The user ID whose orders need to be fetched.
	 * @return A list of orders for the given user.
	 * @throws BadRequestException If order details cannot be fetched.
	 */
	@Operation(summary = "Get orders by user ID", description = "Fetches all orders for the specified user ID.")
	@GetMapping("/OrdersByUserId/{userId}")
	public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable("userId") Long userId) throws BadRequestException{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByUserId(userId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : ORDER DETAILS NOT FETCHED.");
		}
	}
}