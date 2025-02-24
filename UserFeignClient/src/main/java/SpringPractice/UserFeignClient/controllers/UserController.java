package SpringPractice.UserFeignClient.controllers;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import SpringPractice.UserFeignClient.DTOs.Cart_DTO;
import SpringPractice.UserFeignClient.DTOs.LoginRequest;
import SpringPractice.UserFeignClient.DTOs.OrderResponse_DTO;
import SpringPractice.UserFeignClient.feginClientAPIs.Cart_WishListMicroservice;
import SpringPractice.UserFeignClient.feginClientAPIs.OrderMicroservice;
import SpringPractice.UserFeignClient.models.User;
import SpringPractice.UserFeignClient.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(name = "User Controller", description = "APIs for user management")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderMicroservice orderFeginClientAPIs;
    
    @Autowired
    private Cart_WishListMicroservice cart_WishListServices;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    /**
     * Saves a new user.
     */
    @PostMapping("/user")
    @Transactional
    @Operation(summary = "Save a new user", description = "Registers a new user in the system")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        logger.info("Saving user with email: {}", user.getEmailId());
        try {
            if (userService.isMailIdExist(user.getEmailId())) {
                throw new BadRequestException("ERROR : MAIL ID ALREADY EXISTS.");
            } else if (userService.isMobileNoExist(user.getMobileNo())) {
                throw new BadRequestException("ERROR : Mobile No ALREADY EXISTS");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User responseUser = userService.saveUser(user);
            
            Cart_DTO cart = new Cart_DTO();
            cart.setId(responseUser.getId());
            cart_WishListServices.createCart(cart);
            cart_WishListServices.createWishList(responseUser.getId());
            
            return ResponseEntity.status(HttpStatus.OK).body(responseUser);
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    /**
     * Fetches all users.
     */
    @Hidden
    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Retrieves a list of all users")
    public ResponseEntity<List<User>> getAllUsersList() throws BadRequestException {
        logger.info("Fetching all users");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUserList());
        } catch (Exception e) {
            logger.error("Error fetching users: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
    
    /**
     * Fetches user details by user ID.
     */
    @GetMapping("/userById/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieves user details based on the provided user ID")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws BadRequestException {
        logger.info("Fetching user with ID: {}", userId);
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new BadRequestException("ERROR : USER NOT FOUND ON GIVEN ID");
            }
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            logger.error("Error fetching user by ID {}: {}", userId, e.getMessage());
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
    
    /**
     * Fetches order list by user ID.
     */
    @GetMapping("/OrdersByUserId/{userId}")
    @Operation(summary = "Get orders by user ID", description = "Retrieves a list of orders placed by a user")
    public ResponseEntity<List<OrderResponse_DTO>> getOrdersByUserId(@PathVariable("userId") Long userId) throws BadRequestException {
        logger.info("Fetching orders for user ID: {}", userId);
        try {
            if (userService.isUserExists(userId)) {
                return ResponseEntity.status(HttpStatus.OK).body(orderFeginClientAPIs.getOrdersByUserId(userId));
            } else {
                throw new BadRequestException("ERROR : USER NOT FOUND ON GIVEN ID");
            }
        } catch (Exception e) {
            logger.error("Error fetching orders for user ID {}: {}", userId, e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
    
    /**
     * Saves an order for a specific user ID.
     */
    @PostMapping("/OrderOnUser/{userId}")
    @Transactional
    @Operation(summary = "Save order for user", description = "Creates an order for a specific user")
    public ResponseEntity<OrderResponse_DTO> saveOrder(@RequestBody OrderResponse_DTO orderDetails, @PathVariable("userId") Long userId) throws BadRequestException {
        logger.info("Saving order for user ID: {}", userId);
        try {
            if (userService.isUserExists(userId)) {
                orderDetails.setId(null);
                orderDetails.setUserId(userId);
                OrderResponse_DTO order = orderFeginClientAPIs.createOrder(orderDetails);
                userService.sendOrderPacedMail(userId, userId);
                return ResponseEntity.status(HttpStatus.OK).body(order);
            } else {
                throw new BadRequestException("ERROR : USER NOT FOUND WITH GIVEN ID " + userId);
            }
        } catch (Exception e) {
            logger.error("Error saving order for user ID {}: {}", userId, e.getMessage());
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
    
    /**
     * Authenticates a user.
     */
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user with provided credentials")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        logger.info("User login attempt: {}", loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok("User authenticated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }
    }
}
