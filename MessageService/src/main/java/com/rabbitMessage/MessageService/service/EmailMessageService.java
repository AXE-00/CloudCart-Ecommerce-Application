package com.rabbitMessage.MessageService.service;

import com.rabbitMessage.MessageService.config.EcommDTO;
import com.rabbitMessage.MessageService.domain.EmailMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailMessageService implements IEmailMessageService {
    private JavaMailSender javaMailSender;
    @Autowired
    public EmailMessageService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    @RabbitListener(queues = "EmailQueue")
    @Override
    public void sendEmail(EcommDTO ecommDTO) {
        EmailMessage emailMessage = new EmailMessage();

        String to = ecommDTO.getJsonObject().get("to").toString();
        String subject = ecommDTO.getJsonObject().get("subject").toString();
        String message = ecommDTO.getJsonObject().get("message").toString();

        emailMessage.setTo(to);
        emailMessage.setSubject(subject);
        emailMessage.setMessage(message);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("cloudcartecommerce@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Success");
    }

    @Override
    public void userWelcome(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("cloudcartecommerce@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Success");
    }

    @Override
    public int sendOtp(String userEmail) {
        Random ranNum = new Random();
        int otpNum = ranNum.nextInt(1111,9999);
        String emailContent = "Please find below your One-Time Password (OTP) for account verification:\n" +
                              "\n" +
                              "OTP: "+otpNum+"\n" +
                              "\n" +
                              "Kindly enter this OTP within the designated timeframe to complete the verification process.\n" +
                              "\n" +
                              "Best regards,\n" +
                              "Cloud Cart Team";
        String subject ="Supplier Verification.";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(emailContent);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom("cloudcartecommerce@gmail.com");
        simpleMailMessage.setTo(userEmail);
        javaMailSender.send(simpleMailMessage);
        return otpNum;
    }
}
