package Cart.WishlistMicroservice.Cart.Wishlist.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Cart.WishlistMicroservice.Cart.Wishlist.models.Wishlist;
import Cart.WishlistMicroservice.Cart.Wishlist.services.WishListServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for handling wishlist services.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Cart")
@Tag(name = "Wishlist Service", description = "APIs for managing user wishlists")
public class WishListServiceController {

    @Autowired
    private WishListServices wishListService;

    /**
     * Fetches the wishlist for a specific user by user ID.
     * 
     * @param userId the ID of the user
     * @return the user's wishlist
     * @throws BadRequestException if an error occurs
     */
    @GetMapping("/wishList/{userId}")
    @Operation(summary = "Get user's wishlist", description = "Retrieves the wishlist associated with the given user ID.")
    public ResponseEntity<Wishlist> getWishList(@PathVariable("userId") Long userId) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(wishListService.getUsersWishList(userId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    /**
     * Creates a new wishlist for a user.
     * 
     * @param userId the ID of the user
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PostMapping("/createWishList/{userId}")
    @Operation(summary = "Create a wishlist for a user", description = "Creates an empty wishlist for the given user ID.")
    public ResponseEntity<String> createWishList(@PathVariable("userId") Long userId) throws BadRequestException {
        try {
            wishListService.createWish(userId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("WISH LIST CREATED FOR USER " + userId + " WITH LIST ID : " + userId);
        } catch (Exception e) {
            throw new BadRequestException("ERROR : WISH LIST WAS NOT CREATED");
        }
    }

    /**
     * Adds an item to the user's wishlist.
     * 
     * @param userId the ID of the user
     * @param itemId the ID of the item to add
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PostMapping("/wishList/addItem/{userId}/{itemId}")
    @Operation(summary = "Add an item to wishlist", description = "Adds a specified item to the user's wishlist.")
    public ResponseEntity<String> addItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId)
            throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(wishListService.addItemToWishlist(userId, itemId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    
    
    /**
     * Removes an item from the user's wishlist.
     * 
     * @param userId the ID of the user
     * @param itemId the ID of the item to remove
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PostMapping("/wishList/remove/{userId}/{itemId}")
    @Operation(summary = "Remove an item from wishlist", description = "Removes a specified item from the user's wishlist.")
    public ResponseEntity<String> removeItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId)
            throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(wishListService.removeItemFromWishList(userId, itemId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
}
