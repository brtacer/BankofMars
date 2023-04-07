package com.berat.rabbitmq.producer;

import com.berat.rabbitmq.model.ApproveStatusMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveStatusProducer {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.authDirectExchange}")
    private String authDirectExchange;
    @Value("${rabbitmq.registerMailBindingKey}")
    private String registerMailBindingKey;

    public void sendActivationCode(ApproveStatusMailModel model){
        rabbitTemplate.convertAndSend(authDirectExchange,registerMailBindingKey,model);
    }
}
