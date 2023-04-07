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

    @Value("${rabbitmq.accountDirectExchange}")
    private String accountDirectExchange;
    @Value("${rabbitmq.paymentBindingKey}")
    private String paymentBindingKey;
    @Value("${rabbitmq.paymentQueue}")
    private String paymentQueue;
    @Value("${rabbitmq.transferQueue}")
    private String transferQueue;

    @Bean
    DirectExchange accountDirectExchange(){
        return new DirectExchange(accountDirectExchange);
    }

    @Bean
    Queue paymentQueue(){
        return new Queue(paymentQueue);
    }

    @Bean
    Queue transferQueue(){
        return new Queue(transferQueue);
    }

    @Bean
    public Binding paymentBinding(final Queue paymentQueue, final DirectExchange accountDirectExchange){
        return BindingBuilder.bind(paymentQueue).to(accountDirectExchange).with(paymentBindingKey);
    }
}
