package com.berat.rabbitmq.producer;

import com.berat.rabbitmq.model.TransferModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransferProducer {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.transactionDirectExchange}")
    private String transactionDirectExchange;
    @Value("${rabbitmq.transferBindingKey}")
    private String transferBindingKey;

    public void transfer(TransferModel model){
        rabbitTemplate.convertAndSend(transactionDirectExchange,transferBindingKey,model);
    }
}
