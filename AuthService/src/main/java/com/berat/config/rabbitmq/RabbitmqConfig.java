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

    @Value("${rabbitmq.authDirectExchange}")
    private String authDirectExchange;
    @Value("${rabbitmq.registerMailBindingKey}")
    private String registerMailBindingKey;
    @Value("${rabbitmq.registerMailQueueName}")
    private String registerMailQueueName;

    @Bean
    DirectExchange authDirectExchange(){
        return new DirectExchange(authDirectExchange);
    }
    @Bean
    Queue registerMailQueue(){
        return new Queue(registerMailQueueName);
    }
    @Bean
    public Binding registerMailBinding(final Queue registerMailQueue, final DirectExchange authDirectExchange){
        return BindingBuilder.bind(registerMailQueue).to(authDirectExchange).with(registerMailBindingKey);
    }
}
