package SpringPractice.OrderFeignClient.controllers;

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

import SpringPractice.OrderFeignClient.models.Order;
import SpringPractice.OrderFeignClient.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	//adding new Order
	@PostMapping("/order")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) throws BadRequestException{
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(order));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	@GetMapping("/OrdersByUserId/{userId}")
	public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByUserId(userId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : ORDER DETAILS NOT FETCHED.");
		}
	}

}
