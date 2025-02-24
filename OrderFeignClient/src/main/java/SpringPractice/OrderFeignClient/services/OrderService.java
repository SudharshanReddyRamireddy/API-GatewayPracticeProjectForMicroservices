package SpringPractice.OrderFeignClient.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import SpringPractice.OrderFeignClient.models.Order;
import SpringPractice.OrderFeignClient.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	// generating a transaction id with 12 characters;
	public static String generateTrastationId() {
		
		return "TRX" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9);
	}
	
	
	

	public Order saveOrder(Order order) {
		order.setTransationId(OrderService.generateTrastationId());
		return orderRepository.save(order);
	}
	

	
	
	public List<Order> getOrdersByUserId(Long userId) {
		return orderRepository.findOrdersByUserId(userId);
	}
	
	
}
