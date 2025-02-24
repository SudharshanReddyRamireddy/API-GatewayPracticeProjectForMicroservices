package MailService.GMailService.services;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import MailService.GMailService.DTOs.MailRequest_DTO;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServices {
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public String  sendNormalMail(MailRequest_DTO mailDetails) {
		
		SimpleMailMessage gmail = new SimpleMailMessage();
		
		gmail.setTo(mailDetails.getRecipient_mail_id());
		gmail.setSubject(mailDetails.getSubject());
		gmail.setText(mailDetails.getMsgBody());
		javaMailSender.send(gmail);
		
		return "SENT";
	}
	
	
	
	
	
	// method for sending attachments mail
	public String sendAttachmentMail(MailRequest_DTO mailDetails) throws BadRequestException {
	    MimeMessage mimiMessage = javaMailSender.createMimeMessage();
	    
	    try {
	        // ✅ Enable multipart mode
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimiMessage, true);

	        // Set email details
	        messageHelper.setTo(mailDetails.getRecipient_mail_id());
	        messageHelper.setSubject(mailDetails.getSubject());
	        messageHelper.setText(mailDetails.getMsgBody());

	        // 📂 Attach the file
	        File file = new File(mailDetails.getAttachment());
	        if (!file.exists()) {
	            throw new FileNotFoundException("File not found: " + mailDetails.getAttachment());
	        }

	        FileSystemResource fileResource = new FileSystemResource(file);
	        messageHelper.addAttachment(fileResource.getFilename(), fileResource);

	        // Send email
	        javaMailSender.send(mimiMessage);

	        return "MAIL WAS SUCCESSFULLY SENT.";
	    } catch (Exception e) {
	        throw new BadRequestException("ERROR : " + e.getMessage());
	    }
	}


}
