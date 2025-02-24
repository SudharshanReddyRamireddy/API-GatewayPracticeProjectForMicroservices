package Cart.WishlistMicroservice.Cart.Wishlist.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Cart.WishlistMicroservice.Cart.Wishlist.models.Carts;
import Cart.WishlistMicroservice.Cart.Wishlist.services.CartServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for handling cart services.
 */
@RestController
@RequestMapping("/Cart")
@CrossOrigin(origins = "*")
@Tag(name = "Cart Service", description = "APIs for managing shopping carts")
public class CartServiceController {

    @Autowired
    private CartServices cartServices;

    /**
     * Creates a new cart.
     * 
     * @param cart the cart object to create
     */
    @PostMapping("/cart")
    @Operation(summary = "Create a new cart", description = "Creates a cart for a user where the user ID is the same as the cart ID.")
    public void createCart(@RequestBody Carts cart) {
        try {
            cartServices.createCart(cart);
        } catch (Exception e) {
            throw new InternalError("ERROR : WHILE CREATING CART : " + e.getMessage());
        }
    }

    /**
     * Adds an item to the cart.
     * 
     * @param cartId the ID of the cart
     * @param itemId the ID of the item to add
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PutMapping("/addItem/{cartId}/{itemId}")
    @Operation(summary = "Add an item to the cart", description = "Adds a specified item to the given cart.")
    public ResponseEntity<String> addItemToCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cartServices.addItemToCart(cartId, itemId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    /**
     * Fetches cart details by cart ID.
     * 
     * @param cartId the ID of the cart
     * @return the cart details
     * @throws BadRequestException if an error occurs
     */
    @GetMapping("/cart/{cartId}")
    @Operation(summary = "Get cart details", description = "Retrieves the details of a cart using the cart ID.")
    public ResponseEntity<Carts> getCartByCartId(@PathVariable("cartId") Long cartId) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cartServices.getCartByCartId(cartId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    /**
     * Removes an item from the cart.
     * 
     * @param cartId the ID of the cart
     * @param itemId the ID of the item to remove
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PutMapping("/removeItem/{cartId}/{itemId}")
    @Operation(summary = "Remove an item from the cart", description = "Removes a specified item from the given cart.")
    public ResponseEntity<String> removeItemFromCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cartServices.removeItem(cartId, itemId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    /**
     * Clears all items from the cart.
     * 
     * @param cartId the ID of the cart
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PutMapping("/clearCart/{cartId}")
    @Operation(summary = "Clear the cart", description = "Removes all items from the specified cart.")
    public ResponseEntity<String> clearCart(@PathVariable("cartId") Long cartId) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cartServices.clearCart(cartId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
}
