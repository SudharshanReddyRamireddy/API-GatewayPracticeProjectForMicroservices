package SpringPractice.OrderFeignClient.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringPractice.OrderFeignClient.models.Order;
import SpringPractice.OrderFeignClient.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	
	public List<Order> getOrdersByUserId(Long userId){
		return orderRepository.findOrdersByUserId(userId);
	}

}
