package com.berat.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.paymentQueue}")
    private String paymentQueue;
    @Bean
    Queue paymentQueue(){
        return new Queue(paymentQueue);
    }
}
