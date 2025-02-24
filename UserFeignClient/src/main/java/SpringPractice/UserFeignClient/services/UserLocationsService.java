package SpringPractice.UserFeignClient.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import SpringPractice.UserFeignClient.models.Location;
import SpringPractice.UserFeignClient.repositories.LocationRepository;

@Service
public class UserLocationsService {
	
	private LocationRepository locationRepository;
	

	
	public UserLocationsService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	//saving new location 
	public Location addLocation(Location locationDetails) {
	
		
		return locationRepository.save(locationDetails);
		
	}
	
	
	//fetching list of locations by user id
	public List<Location> locationsByUserId(Long userId){
		return locationRepository.findByUserId(userId);
	}
	
	
	// updating location details with new location details based on user id
	public Location updateLocationDetailsByUserId(Location locationdetails) throws BadRequestException {
		
		Location responseLocationDetails = locationRepository.findById(locationdetails.getLocationId()).orElseThrow(() -> new NoSuchElementException("LOCATION DETAILS NOT FOUND NO GIVEN LOCATION ID"));
		
		if(responseLocationDetails.getUserId() == locationdetails.getUserId()) {

			responseLocationDetails.setCountry(locationdetails.getCountry());
			responseLocationDetails.setPincode(locationdetails.getPincode());
			responseLocationDetails.setAddress(locationdetails.getAddress());
			responseLocationDetails.setType(locationdetails.getType());
			responseLocationDetails.setState(locationdetails.getState());
			return locationRepository.save(responseLocationDetails);
		}else {
			throw new BadRequestException("LOCATION NOT FOUND ON GIVEN USER ID");
		}
	}
	
	
	
	//Deleting location details from bd based on user id with location id
	public String deleteLocation(Long userId, Long locationId) throws BadRequestException {
		
		Location responseLocation = locationRepository.findById(locationId).orElseThrow(() -> new BadRequestException("LOCATION NOT FOUND ON GIVEN LOCATION ID"));
		
		if(responseLocation.getUserId() == userId) {
			locationRepository.deleteById(locationId);
			return "LOCATION DELETED SUCCESSFULLY";
		}else {
			throw new BadRequestException("LOATION NOT FOUND ON GIVEN USER ID");
		}
	}
}
