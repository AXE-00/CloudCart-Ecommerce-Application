package com.rabbitMessage.MessageService.service;

import com.rabbitMessage.MessageService.config.EcommDTO;

public interface IEmailMessageService {
    void sendEmail(EcommDTO ecommDTO);
    public void userWelcome(String to, String subject, String message);
    public void reqApproved(EcommDTO ecommDTO);
    public int sendOtp(String userEmail);
}
