package com.berat.converter;

import com.berat.dto.request.PaymentTransactionRequest;
import com.berat.dto.request.TransferTransactionRequest;
import com.berat.dto.response.TransactionResponse;
import com.berat.model.Transaction;
import com.berat.model.Type;
import com.berat.rabbitmq.model.TransferModel;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionConverter {

    public static Transaction toTransaction(TransferTransactionRequest dto){
        return Transaction.builder()
                .destinationIbanNumber(dto.getDestinationIbanNumber())
                .quantity(dto.getQuantity())
                .type(Type.TRANSFER)
                .accountId(dto.getAccountId())
                .build();
    }
    public static Transaction toTransaction(PaymentTransactionRequest dto){
        return Transaction.builder()
                .cardId(dto.getCardId())
                .quantity(dto.getQuantity())
                .type(Type.PAYMENT)
                .accountId(dto.getAccountId())
                .build();
    }
    public static TransactionResponse toTransactionResponse(Transaction transaction){
        return TransactionResponse.builder()
                .createDate(new Date(transaction.getCreateDate()))
                .id(transaction.getId())
                .type(transaction.getType().name())
                .destinationIbanNumber(transaction.getDestinationIbanNumber())
                .cardId(transaction.getCardId())
                .quantity(transaction.getQuantity())
                .accountId(transaction.getAccountId())
                .build();
    }
    public static TransferModel toTransferModel(TransferTransactionRequest dto){
        return TransferModel.builder()
                .accountId(dto.getAccountId())
                .quantity(dto.getQuantity())
                .destinationIbanNumber(dto.getDestinationIbanNumber())
                .build();
    }
    public static TransferModel toTransferModel(PaymentTransactionRequest dto){
        return TransferModel.builder()
                .accountId(dto.getAccountId())
                .quantity(dto.getQuantity())
                .cardId(dto.getCardId())
                .build();
    }
}
