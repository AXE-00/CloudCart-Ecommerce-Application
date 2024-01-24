package com.rabbitMessage.MessageService.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
