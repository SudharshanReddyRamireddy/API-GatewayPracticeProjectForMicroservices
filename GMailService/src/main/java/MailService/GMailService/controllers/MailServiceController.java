package MailService.GMailService.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MailService.GMailService.DTOs.MailRequest_DTO;
import MailService.GMailService.services.MailServices;

@RestController
@RequestMapping("/Gmail")
public class MailServiceController {
	
	@Autowired
	private MailServices mailServices;
	
	
	//sending normal mail
	@PostMapping("/send")
	public ResponseEntity<String> sentMail(@RequestBody MailRequest_DTO mailDetails) throws BadRequestException{
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(mailServices.sendNormalMail(mailDetails));
			
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
		
	}
	
	
	

}
