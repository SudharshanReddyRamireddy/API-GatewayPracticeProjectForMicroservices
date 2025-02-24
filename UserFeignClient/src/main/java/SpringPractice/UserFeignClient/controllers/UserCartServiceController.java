package SpringPractice.UserFeignClient.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SpringPractice.UserFeignClient.DTOs.Cart_DTO;
import SpringPractice.UserFeignClient.feginClientAPIs.Cart_WishListMicroservice;
import SpringPractice.UserFeignClient.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller to manage user cart services.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(name = "User Cart Service", description = "APIs for managing user cart operations")
public class UserCartServiceController {
    
    @Autowired
    UserService userServices;
    
    @Autowired
    Cart_WishListMicroservice cartServices;
    
    /**
     * Retrieve cart items for a specific user.
     * @param userId The ID of the user whose cart items need to be fetched.
     * @return ResponseEntity containing the cart details.
     * @throws BadRequestException if the user is not found or any other error occurs.
     */
    @GetMapping("/cart/{userId}")
    @Operation(summary = "Get Cart by User ID", description = "Fetches the cart details for a given user ID.")
    public ResponseEntity<Cart_DTO> getCart(
            @Parameter(description = "User ID for retrieving cart") @PathVariable("userId") Long userId
    ) throws BadRequestException {
        
        try {
            
            if (userServices.isUserExists(userId)) {
                return ResponseEntity.status(HttpStatus.OK).body(cartServices.getCartByCartId(userId));
            } else {
                throw new BadRequestException("ERROR : USER NOT FOUND ON ID : " + userId);
            }
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
    
    /**
     * Add an item to the respective user's cart.
     * @param userId The ID of the user.
     * @param itemId The ID of the item to add.
     * @return ResponseEntity containing success message.
     * @throws BadRequestException if the user is not found or an error occurs.
     */
    @PutMapping("/addItem/{userId}/{itemId}")
    @Operation(summary = "Add Item to Cart", description = "Adds an item to the specified user's cart.")
    public ResponseEntity<String> addItem(
            @Parameter(description = "User ID") @PathVariable("userId") Long userId,
            @Parameter(description = "Item ID") @PathVariable("itemId") Long itemId
    ) throws BadRequestException {
        
        try {
            if (userServices.isUserExists(userId)) {
                String response = cartServices.addItemToCart(userId, itemId);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                throw new BadRequestException("ERROR : USER NOT FOUND ON ID : " + userId);
            }
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
    
    /**
     * Clears a user's cart.
     * @param cartId The ID of the cart to clear.
     * @return ResponseEntity containing success message.
     * @throws BadRequestException if an error occurs.
     */
    @PutMapping("/clear/{cartId}")
    @Operation(summary = "Clear Cart", description = "Removes all items from the specified cart.")
    public ResponseEntity<String> clearCart(
            @Parameter(description = "Cart ID") @PathVariable("cartId") Long cartId
    ) throws BadRequestException {
        
        try {
            System.out.println("1");
            return ResponseEntity.status(HttpStatus.OK).body(cartServices.clearCart(cartId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
    
    /**
     * Remove an item from the user's cart.
     * @param userId The ID of the user.
     * @param itemId The ID of the item to remove.
     * @return ResponseEntity containing success message.
     * @throws BadRequestException if an error occurs.
     */
    @PutMapping("/cart/item/remove/{userId}/{itemId}")
    @Operation(summary = "Remove Item from Cart", description = "Removes a specified item from the user's cart.")
    public ResponseEntity<String> removeItem(
            @Parameter(description = "User ID") @PathVariable("userId") Long userId,
            @Parameter(description = "Item ID") @PathVariable("itemId") Long itemId
    ) throws BadRequestException {
        
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cartServices.removeItemFromCart(userId, itemId)).getBody();
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
}