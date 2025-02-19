package SpringPractice.UserFeignClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserFeignClientApplication.class, args);
	}

	
	//main class
}
