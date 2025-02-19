package SpringPractice.OrderFeignClient.FeignClientAPIs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ITEMS-SERVICE")
public interface ItemMicroservice {
	
	@GetMapping("/items/isItemExist/{itemId}")
	Boolean isItemExist(@PathVariable("itemId") Long itemId);

}
