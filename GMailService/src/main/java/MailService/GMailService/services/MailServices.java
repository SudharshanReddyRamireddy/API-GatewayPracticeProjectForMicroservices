package MailService.GMailService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import MailService.GMailService.DTOs.MailRequest_DTO;

@Service
public class MailServices {
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public String  sendNormalMail(MailRequest_DTO mailDetails) {
		
		SimpleMailMessage GMAIL = new SimpleMailMessage();
		
		GMAIL.setTo(mailDetails.getRecipient_mail_id());
		GMAIL.setSubject(mailDetails.getSubject());
		GMAIL.setText(mailDetails.getMsgBody());
		javaMailSender.send(GMAIL);
		
		return "SENT";
	}

}
