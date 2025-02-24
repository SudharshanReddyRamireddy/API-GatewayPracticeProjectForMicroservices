package ItemsMicroservice.Items.controllers;

import ItemsMicroservice.Items.DTOs.ItemResponse;
import ItemsMicroservice.Items.models.Item;
import ItemsMicroservice.Items.services.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controller for managing items in the system.
 */
@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*")
@Tag(name = "Item Controller", description = "APIs for managing items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Method to check presence of item by item ID.
     * @param itemId ID of the item
     * @return true if the item exists, false otherwise
     */
    @Operation(summary = "Check if an item exists", description = "Returns true if the item exists, false otherwise")
    @GetMapping("/isItemExist/{itemId}")
    public ResponseEntity<Boolean> isItemExist(@PathVariable("itemId") Long itemId) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.isItemExist(itemId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }

    /**
     * Uploads an item with details.
     * @param itemDetails Item details
     * @return Response message
     */
    @Operation(summary = "Upload an item", description = "Saves an item with the provided details")
    @PostMapping("/item")
    public ResponseEntity<String> uploadItem(@RequestBody ItemResponse itemDetails) {
        try {
            Item savedItem = itemService.saveItem(itemDetails);
            return ResponseEntity.ok("Item uploaded successfully with ID: " + savedItem.getId());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves all items.
     * @return List of items
     */
    @Operation(summary = "Get all items", description = "Retrieves all available items")
    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    /**
     * Retrieves an item by ID.
     * @param id Item ID
     * @return Item details
     */
    @Operation(summary = "Get an item by ID", description = "Retrieves the details of an item based on its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    /**
     * Retrieves the price of an item by its ID.
     * @param itemId Item ID
     * @return Price of the item
     */
    @Operation(summary = "Get item price", description = "Retrieves the price of an item based on its ID")
    @GetMapping("/itemPrice/{itemId}")
    public ResponseEntity<Double> getItemPrice(@PathVariable("itemId") Long itemId) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemPrice(itemId));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
}