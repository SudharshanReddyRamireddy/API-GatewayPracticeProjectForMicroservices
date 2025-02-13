package SpringPractice.OrderFeignClient.FeignClientAPIs;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-SERVICE")//url = "http://localhost:8090"
public interface UserMicroservice {

}
