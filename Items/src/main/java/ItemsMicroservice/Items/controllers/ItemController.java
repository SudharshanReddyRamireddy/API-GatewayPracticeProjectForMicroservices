package ItemsMicroservice.Items.controllers;

import ItemsMicroservice.Items.DTOs.ItemResponse;
import ItemsMicroservice.Items.models.Item;
import ItemsMicroservice.Items.services.ItemService;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    

    
    //method to check presence of item by item id
    @GetMapping("/isItemExist/{itemId}")
    public ResponseEntity<Boolean> isItemExist(@PathVariable("itemId") Long itemId) throws BadRequestException{
    	
    	try {
    		return ResponseEntity.status(HttpStatus.OK).body(itemService.isItemExist(itemId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}	
    }
    
    
    
    

    // Upload item with image
    @PostMapping("/item")
    public ResponseEntity<String> uploadItem(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("brandName") String brandName,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile file) {
        try {
            Item savedItem = itemService.saveItem(name, type, brandName, price, file);
            return ResponseEntity.ok("Item uploaded successfully with ID: " + savedItem.getId());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
    
    
    
    

    // Get all items
    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }
    
    
    
    
   
    // Get a single item by ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }
    
    
    
    @GetMapping("/itemPrice/{itemId}")
    public ResponseEntity<Double> getItemPrice(@PathVariable("itemId") Long itemId) throws BadRequestException{
    	
    	try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemPrice(itemId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
    	
    }
    
}