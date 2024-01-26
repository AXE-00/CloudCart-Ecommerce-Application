package com.rabbitMessage.MessageService.contoller;
import com.rabbitMessage.MessageService.domain.EmailMessage;
import com.rabbitMessage.MessageService.service.IEmailMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emailService")
public class MessageController {
    private IEmailMessageService iEmailMessageService;

    @Autowired
    public MessageController(IEmailMessageService iEmailMessageService) {
        this.iEmailMessageService = iEmailMessageService;
    }

    @GetMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailMessage emailMessage){
        this.iEmailMessageService.userWelcome(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return  ResponseEntity.ok("Successfully send");
    }

    @GetMapping("/send-otp/{email}")
    public ResponseEntity<?> sendOtp(@PathVariable String email){
        return new ResponseEntity<>(iEmailMessageService.sendOtp(email), HttpStatus.OK);
    }
}
