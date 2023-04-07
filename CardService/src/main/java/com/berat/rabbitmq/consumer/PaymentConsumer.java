package com.berat.rabbitmq.consumer;

import com.berat.rabbitmq.model.PaymentModel;
import com.berat.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {
    private final CardService cardService;

    @RabbitListener(queues = "${rabbitmq.paymentQueue}")
    public void payment(PaymentModel model){
        log.info("Payment {}",model.toString());
        cardService.debtPayment(model);
    }
}
