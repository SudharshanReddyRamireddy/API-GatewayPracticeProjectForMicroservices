package SpringPractice.OrderFeignClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderFeignClientApplication.class, args);
	}

}
