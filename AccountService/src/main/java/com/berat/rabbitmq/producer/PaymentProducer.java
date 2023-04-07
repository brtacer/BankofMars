package com.berat.rabbitmq.producer;

import com.berat.rabbitmq.model.PaymentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.accountDirectExchange}")
    private String accountDirectExchange;
    @Value("${rabbitmq.paymentBindingKey}")
    private String paymentBindingKey;

    public void payment(PaymentModel model){
        rabbitTemplate.convertAndSend(accountDirectExchange,paymentBindingKey,model);
    }
}
