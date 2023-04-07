package com.berat.rabbitmq.consumer;

import com.berat.rabbitmq.model.TransferModel;
import com.berat.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferConsumer {
    private final AccountService accountService;

    @RabbitListener(queues = "${rabbitmq.transferQueue}")
    public void transfer(TransferModel model){
        log.info("Transfer {}",model.toString());
        accountService.transferMoney(model);
    }
}
