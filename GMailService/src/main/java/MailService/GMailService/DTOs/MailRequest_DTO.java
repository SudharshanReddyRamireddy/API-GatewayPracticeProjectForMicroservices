package MailService.GMailService.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest_DTO {
	
	private String recipient_mail_id;
	private String subject;
	private String msgBody;
	private String attachment;
	

}
