package MailService.GMailService.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MailService.GMailService.DTOs.MailRequest_DTO;
import MailService.GMailService.services.MailServices;

/**
 * Controller for handling mail services.
 */
@RestController
@RequestMapping("/Gmail")
@CrossOrigin(origins = "*")
@Tag(name = "Mail Service", description = "APIs for sending emails")
public class MailServiceController {
    
    @Autowired
    private MailServices mailServices;
    
    /**
     * Sends a normal email.
     * 
     * @param mailDetails the details of the email to send
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PostMapping("/send")
    @Operation(summary = "Send a normal email", description = "Sends an email without attachments")
    public ResponseEntity<String> sentMail(@RequestBody MailRequest_DTO mailDetails) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mailServices.sendNormalMail(mailDetails));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
    
    /**
     * Sends an email with attachments.
     * 
     * @param mailDetails the details of the email to send
     * @return response message indicating success or failure
     * @throws BadRequestException if an error occurs
     */
    @PostMapping("/sendAttachmentMail")
    @Operation(summary = "Send an email with attachments", description = "Sends an email including file attachments")
    public ResponseEntity<String> sentAttachmentMail(@RequestBody MailRequest_DTO mailDetails) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mailServices.sendAttachmentMail(mailDetails));
        } catch (Exception e) {
            throw new BadRequestException("ERROR : " + e.getMessage());
        }
    }
}