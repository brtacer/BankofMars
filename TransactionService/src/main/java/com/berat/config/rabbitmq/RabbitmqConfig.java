package com.berat.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.transactionDirectExchange}")
    private String transactionDirectExchange;
    @Value("${rabbitmq.transferBindingKey}")
    private String transferBindingKey;
    @Value("${rabbitmq.transferQueue}")
    private String transferQueue;


    @Bean
    DirectExchange transactionDirectExchange(){
        return new DirectExchange(transactionDirectExchange);
    }
    @Bean
    Queue transferQueue(){
        return new Queue(transferQueue);
    }
    @Bean
    public Binding transferBinding(final Queue transferQueue, final DirectExchange transactionDirectExchange){
        return BindingBuilder.bind(transferQueue).to(transactionDirectExchange).with(transferBindingKey);
    }
}
