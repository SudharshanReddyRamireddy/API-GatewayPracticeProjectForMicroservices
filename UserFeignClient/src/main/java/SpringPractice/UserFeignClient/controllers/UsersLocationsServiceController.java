package SpringPractice.UserFeignClient.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import SpringPractice.UserFeignClient.models.Location;
import SpringPractice.UserFeignClient.services.UserLocationsService;
import SpringPractice.UserFeignClient.services.UserService;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/users")
public class UsersLocationsServiceController {
	
	
	//* register new address for user 
	//* fetching location details based on user id
	//* update address details for user
	//* delete address detail for user
	private UserLocationsService userLocationService;
	private UserService userService;
	
	public UsersLocationsServiceController(UserLocationsService userLocationService,UserService userService) {
		this.userLocationService = userLocationService;
		this.userService = userService;
	}
	
	
	//saving new location on registered user id
	@PostMapping("/location")
	public ResponseEntity<Location> saveLocation(@Valid @RequestBody Location location) throws BadRequestException{
		
		try {
			if(userService.isUserExists(location.getUserId())) {
				return ResponseEntity.status(HttpStatus.OK).body(userLocationService.addLocation(location));
			}else {
				throw new BadRequestException("ERROR INVALID USER ID : USER NOT FOUND ON GIVEN USER ID");
			}
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
		
	}
	
	
	
	
	//fetching list of all location details based on user id
	@GetMapping("/locations/{userId}")
	public ResponseEntity<List<Location>> getAllLocationsByUserId(@PathVariable("userId") Long userId) throws BadRequestException{
		
		try {
			
			if(userService.isUserExists(userId)) {
				return ResponseEntity.status(HttpStatus.OK).body(userLocationService.locationsByUserId(userId));
			}else {
				throw new BadRequestException("INVALID USER ID : USER NOT FOUND ON GIVEN USER ID");
			}
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	//update location details
	@PatchExchange("/location")
	public ResponseEntity<Location> editLocationDetils(@RequestBody Location locationDetails) throws BadRequestException{
		
		try {
			if(userService.isUserExists(locationDetails.getUserId())) {
				return ResponseEntity.status(HttpStatus.OK).body(userLocationService.updateLocationDetailsByUserId(locationDetails));
			}else {
				throw new BadRequestException("INVALID USER ID : USER NOT FOUND ON GIVEN USER ID");
			}
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
	}
	
	
	//deleting location 
	@DeleteMapping("/location/{userId}/{locationId}")
	public ResponseEntity<String> deleteLocation(@PathVariable("userId") Long userId, @PathVariable("locationId") Long locationId) throws BadRequestException{

		try {
			
			if(userService.isUserExists(userId)) {
				return ResponseEntity.status(HttpStatus.OK).body(userLocationService.deleteLocation(userId, locationId));
			}else {
				throw new BadRequestException("INVALID USER : USER NOT FOUND ON GIVEN USER ID");
			}
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
		
	}
}
