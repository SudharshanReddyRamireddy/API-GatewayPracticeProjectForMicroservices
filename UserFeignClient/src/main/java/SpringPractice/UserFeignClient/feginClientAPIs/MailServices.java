package SpringPractice.UserFeignClient.feginClientAPIs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import SpringPractice.UserFeignClient.DTOs.MailRequest_DTO;


@FeignClient(name = "GMAILSERVICE")
public interface MailServices {

	@PostMapping("/Gmail/send")
	public String sentMail(@RequestBody MailRequest_DTO mailDetails);
	
	
}
