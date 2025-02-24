package SpringPractice.UserFeignClient.controllers;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;
import SpringPractice.UserFeignClient.models.Location;
import SpringPractice.UserFeignClient.services.UserLocationsService;
import SpringPractice.UserFeignClient.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller for managing user locations.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(name = "User Locations", description = "APIs for managing user locations")
public class UsersLocationsServiceController {
    
    private static final Logger logger = LoggerFactory.getLogger(UsersLocationsServiceController.class);
    
    @Autowired
    private UserLocationsService userLocationService;
    @Autowired
    private UserService userService;
    
//    public UsersLocationsServiceController(UserLocationsService userLocationService, UserService userService) {
//        this.userLocationService = userLocationService;
//        this.userService = userService;
//    }
    
    /**
     * Save a new location for a registered user.
     */
    @Operation(summary = "Save user location", description = "Saves a new location for a registered user ID")
    @PostMapping("/location")
    public ResponseEntity<Location> saveLocation(@Valid @RequestBody Location location) throws BadRequestException {
        logger.info("Request received to save location for user ID: {}", location.getUserId());
        try {
            if (userService.isUserExists(location.getUserId())) {
                return ResponseEntity.status(HttpStatus.OK).body(userLocationService.addLocation(location));
            } else {
                throw new BadRequestException("ERROR INVALID USER ID: USER NOT FOUND ON GIVEN USER ID");
            }
        } catch (Exception e) {
            logger.error("Error while saving location: {}", e.getMessage());
            throw new BadRequestException("ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Fetch all location details based on user ID.
     */
    @Operation(summary = "Get user locations", description = "Fetches all locations associated with a given user ID")
    @GetMapping("/locations/{userId}")
    public ResponseEntity<List<Location>> getAllLocationsByUserId(@PathVariable("userId") Long userId) throws BadRequestException {
        logger.info("Fetching locations for user ID: {}", userId);
        try {
            if (userService.isUserExists(userId)) {
                return ResponseEntity.status(HttpStatus.OK).body(userLocationService.locationsByUserId(userId));
            } else {
                throw new BadRequestException("INVALID USER ID: USER NOT FOUND ON GIVEN USER ID");
            }
        } catch (Exception e) {
            logger.error("Error while fetching locations: {}", e.getMessage());
            throw new BadRequestException("ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Update location details for a user.
     */
    @Operation(summary = "Update user location", description = "Updates location details for a user")
    @PatchExchange("/location")
    public ResponseEntity<Location> editLocationDetails(@RequestBody Location locationDetails) throws BadRequestException {
        logger.info("Request received to update location for user ID: {}", locationDetails.getUserId());
        try {
            if (userService.isUserExists(locationDetails.getUserId())) {
                return ResponseEntity.status(HttpStatus.OK).body(userLocationService.updateLocationDetailsByUserId(locationDetails));
            } else {
                throw new BadRequestException("INVALID USER ID: USER NOT FOUND ON GIVEN USER ID");
            }
        } catch (Exception e) {
            logger.error("Error while updating location: {}", e.getMessage());
            throw new BadRequestException("ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Delete a location for a specific user ID and location ID.
     */
    @Operation(summary = "Delete user location", description = "Deletes a specific location for a given user ID and location ID")
    @DeleteMapping("/location/{userId}/{locationId}")
    public ResponseEntity<String> deleteLocation(@PathVariable("userId") Long userId, @PathVariable("locationId") Long locationId) throws BadRequestException {
        logger.info("Request received to delete location ID: {} for user ID: {}", locationId, userId);
        try {
            if (userService.isUserExists(userId)) {
                return ResponseEntity.status(HttpStatus.OK).body(userLocationService.deleteLocation(userId, locationId));
            } else {
                throw new BadRequestException("INVALID USER: USER NOT FOUND ON GIVEN USER ID");
            }
        } catch (Exception e) {
            logger.error("Error while deleting location: {}", e.getMessage());
            throw new BadRequestException("ERROR: " + e.getMessage());
        }
    }
}
