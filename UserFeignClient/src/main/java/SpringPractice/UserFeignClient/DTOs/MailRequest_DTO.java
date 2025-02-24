package SpringPractice.UserFeignClient.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest_DTO {
	
	private String recipient_mail_id;
	private String subject;
	private String msgBody;
	private String attachment;
	
	

}
