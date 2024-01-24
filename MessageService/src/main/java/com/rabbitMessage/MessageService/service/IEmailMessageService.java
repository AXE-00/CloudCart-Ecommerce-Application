package com.rabbitMessage.MessageService.service;

import com.rabbitMessage.MessageService.config.EcommDTO;

public interface IEmailMessageService {
    void sendEmail(EcommDTO ecommDTO);
    public void userWelcome(String to, String subject, String message);
    public int sendOtp(String name,String email);
}
