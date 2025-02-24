package SpringPractice.UserFeignClient.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringPractice.UserFeignClient.DTOs.Cart_DTO;
import SpringPractice.UserFeignClient.DTOs.OrderResponse_DTO;
import SpringPractice.UserFeignClient.feginClientAPIs.Cart_WishListMicroservice;
import SpringPractice.UserFeignClient.feginClientAPIs.OrderMicroservice;
import SpringPractice.UserFeignClient.models.Location;

@Service
public class UserOrderService {
	
	@Autowired
	private Cart_WishListMicroservice cartMicroservice;
	
	@Autowired
	private OrderMicroservice orderMicroservice;
	
	@Autowired
	private UserLocationsService userLocationService;
	
	
	// method to proceed cart check out order
	public OrderResponse_DTO cartCheckOut(Long userId,Long locationId) throws BadRequestException {
		
		
		List<Location> userRegistredLocations = userLocationService.locationsByUserId(userId);

		Boolean isLocationExist = userRegistredLocations.stream()
		    .anyMatch(location -> location.getLocationId() == locationId);
		
		if(isLocationExist) {
			
			Cart_DTO cart = cartMicroservice.getCartByCartId(userId);
			
			OrderResponse_DTO orderDetails = new OrderResponse_DTO();
			orderDetails.setItems(cart.getCartItems());
			orderDetails.setUserId(userId);
			orderDetails.setDateOfOrder(LocalDate.now());
			orderDetails.setTotalCost(cart.getCartPrice());
			orderDetails.setLocationId(locationId);
			
			return orderMicroservice.createOrder(orderDetails);
			
		}else {
			throw new BadRequestException("INVALID LOCATION : LOCATION NOT FOUND ON RESPECTIVE USER REGISTRED LOCATIONS");
		}

	}
	

}
