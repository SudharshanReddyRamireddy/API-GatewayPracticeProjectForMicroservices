package SpringPractice.UserFeignClient.controllers;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import SpringPractice.UserFeignClient.DTOs.Wishlist_DTO;
import SpringPractice.UserFeignClient.feginClientAPIs.Cart_WishListMicroservice;

@RestController
@RequestMapping("/Cart")
@CrossOrigin(origins = "*")
@Tag(name = "USERS WISHLIST CONTROLLER", description = "APIs for managing user wishlist")
public class UserWishlistController {
    
    // Logger instance for debugging and monitoring
    private static final Logger logger = LoggerFactory.getLogger(UserWishlistController.class);

    @Autowired
    private Cart_WishListMicroservice wishListServices;

    /**
     * Fetches the user's wishlist items by user ID.
     *
     * @param userId ID of the user whose wishlist is to be retrieved.
     * @return ResponseEntity containing the Wishlist_DTO.
     * @throws BadRequestException if an error occurs.
     */
    @Operation(summary = "Fetch user's wishlist", description = "Retrieves all wishlist items for a given user ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved wishlist"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID or request error")
    })
    @GetMapping("/wishList/{userId}")
    public ResponseEntity<Wishlist_DTO> getWishList(@PathVariable("userId") Long userId) throws BadRequestException {
        logger.info("Fetching wishlist for user ID: {}", userId);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(wishListServices.getWishList(userId).getBody());
        } catch (Exception e) {
            logger.error("Error while fetching wishlist for user ID {}: {}", userId, e.getMessage());
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    /**
     * Adds an item to the user's wishlist.
     *
     * @param userId ID of the user.
     * @param itemId ID of the item to be added.
     * @return ResponseEntity with success message.
     * @throws BadRequestException if an error occurs.
     */
    @Operation(summary = "Add item to wishlist", description = "Adds an item to the user's wishlist.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item added to wishlist successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID or item ID")
    })
    @PostMapping("/addItem/{userId}/{itemId}")
    public ResponseEntity<String> addItemToWishlist(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId) throws BadRequestException {
        logger.info("Adding item ID {} to wishlist for user ID {}", itemId, userId);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(wishListServices.addItem(userId, itemId));
        } catch (Exception e) {
            logger.error("Error while adding item ID {} to wishlist for user ID {}: {}", itemId, userId, e.getMessage());
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    /**
     * Removes an item from the user's wishlist.
     *
     * @param userId ID of the user.
     * @param itemId ID of the item to be removed.
     * @return ResponseEntity with success message.
     * @throws BadRequestException if an error occurs.
     */
    @Operation(summary = "Remove item from wishlist", description = "Removes an item from the user's wishlist.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item removed from wishlist successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID or item ID")
    })
    @PutMapping("/removeItem/{userId}/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId) throws BadRequestException {
        logger.info("Removing item ID {} from wishlist for user ID {}", itemId, userId);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(wishListServices.removeItem(userId, itemId).getBody());
        } catch (Exception e) {
            logger.error("Error while removing item ID {} from wishlist for user ID {}: {}", itemId, userId, e.getMessage());
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
}
