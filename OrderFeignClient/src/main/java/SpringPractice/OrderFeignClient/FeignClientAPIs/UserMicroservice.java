package SpringPractice.OrderFeignClient.FeignClientAPIs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserMicroservice {
	
	@GetMapping("/users/isUserExists/{userId}")
    public ResponseEntity<Boolean> isUserExist(@PathVariable("userId") Long userId);

}
