package ItemsMicroservice.Items.services;

import ItemsMicroservice.Items.DTOs.ItemResponse;
import ItemsMicroservice.Items.models.Item;
import ItemsMicroservice.Items.repositories.ItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    public Item saveItem(String name, String type, String brandName, Double price, MultipartFile file) throws IOException {
        Item item = new Item();
        item.setName(name);
        item.setType(type);
        item.setBrandName(brandName);
        item.setPrice(price);
        System.out.println(file.getBytes());
        item.setImage(file.getBytes()); // Convert file to byte[]

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
}
