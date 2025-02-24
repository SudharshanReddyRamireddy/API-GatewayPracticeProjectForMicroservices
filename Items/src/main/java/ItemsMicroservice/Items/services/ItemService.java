package ItemsMicroservice.Items.services;

import ItemsMicroservice.Items.DTOs.ItemResponse;
import ItemsMicroservice.Items.models.Item;
import ItemsMicroservice.Items.repositories.ItemRepository;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    
    // checking item presence
    public Boolean isItemExist(Long itemId) {
    	
    	if(itemRepository.findById(itemId).isPresent()) {
    		return true;
    	}else {
    		return false;
    	}
    	
    }
    
    

    // Method to save item with image
    public Item saveItem(ItemResponse itemDetails) throws IOException {
        Item item = new Item();
        item.setName(itemDetails.getName());
        item.setType(itemDetails.getType());
        item.setBrandName(itemDetails.getBrandName());
        item.setPrice(itemDetails.getPrice());
        if(itemDetails.getBase64Image().isEmpty() || itemDetails.getBase64Image() == null) {
        	
        }else {
        	item.setImage(itemDetails.getBase64Image().getBytes()); // Convert file to byte[]
        }

        return itemRepository.save(item);
    }

    // Method to fetch all items with images in Base64 format
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> new ItemResponse(
                        item.getId(),
                        item.getName(),
                        item.getType(),
                        item.getBrandName(),
                        item.getPrice(),
                        Base64.getEncoder().encodeToString(item.getImage()) // Convert byte[] to Base64
                ))
                .collect(Collectors.toList());
    }
    
    

   
    // Method to fetch a single item by ID
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        System.out.println(item.getImage());
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getBrandName(),
                item.getPrice(),
                Base64.getEncoder().encodeToString(item.getImage()) // Convert byte[] to Base64
        );
    }
    
    
    
    //Method to return item price by item id;
    public Double getItemPrice(Long itemId) throws BadRequestException {
    	
    	Item item = itemRepository.findById(itemId).orElseThrow(() -> new BadRequestException("ITEM NOT FOUND ON ID : " + itemId));
    	return item.getPrice();
    }
    
    
    
    
    
    
    
}
