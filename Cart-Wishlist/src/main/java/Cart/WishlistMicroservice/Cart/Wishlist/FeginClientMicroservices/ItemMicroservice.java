package Cart.WishlistMicroservice.Cart.Wishlist.FeginClientMicroservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ITEMS-SERVICE")
public interface ItemMicroservice {
	
	
	@GetMapping("/items/isItemExist/{itemId}")
	public Boolean isItemExist(@PathVariable("itemId") Long itemId);
	
	@GetMapping("/items/itemPrice/{itemId}")
    public Double getItemPrice(@PathVariable("itemId") Long itemId);

}
