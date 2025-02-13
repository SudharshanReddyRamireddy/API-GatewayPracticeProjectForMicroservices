package SpringPractice.UserFeignClient.feginClientAPIs;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import SpringPractice.UserFeignClient.DTOs.OrderResponse_DTO;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderMicroservice {
	
	@PostMapping("/order")
    OrderResponse_DTO createOrder(@RequestBody OrderResponse_DTO order);

    // fetching all orders by user id
    @GetMapping("/OrdersByUserId/{userId}")
    List<OrderResponse_DTO> getOrdersByUserId(@PathVariable("userId") Long userId);
    
    
    

}
