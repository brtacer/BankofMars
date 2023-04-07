package com.berat.rabbitmq.consumer;

import com.berat.rabbitmq.model.ApproveStatusMailModel;
import com.berat.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveStatusConsumer {

    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "${rabbitmq.registerMailQueueName}")
    public void sendActivationCode(ApproveStatusMailModel model){
        mailSenderService.sendMail(model);
    }
}
