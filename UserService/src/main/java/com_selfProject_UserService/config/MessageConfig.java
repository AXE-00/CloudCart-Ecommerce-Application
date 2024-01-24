package com_selfProject_UserService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    private String exchangeName="CloudExchange";
    private String queueName = "EmailQueue";

    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }
    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getJackson2JsonConverter());
        return rabbitTemplate;
    }
    @Bean
    public Binding getBinding(){
        return BindingBuilder.bind(queue()).to(getDirectExchange()).with("Cloud-Key");
    }
}
