package com.berat.converter;

import com.berat.dto.request.AccountCreateRequest;
import com.berat.dto.response.AccountResponse;
import com.berat.model.Account;
import com.berat.model.Currency;
import com.berat.model.Type;
import com.berat.rabbitmq.model.PaymentModel;
import com.berat.rabbitmq.model.TransferModel;
import com.berat.util.Generator;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public static Account toAccount(AccountCreateRequest dto){
        return Account.builder()
                .accountNumber(Generator.randomAccountNumber())
                .iban(Generator.randomIban())
                .type(Type.valueOf(dto.getType()))
                .currency(Currency.valueOf(dto.getCurrency()))
                .customerId(dto.getCustomerId())
                .build();
    }
    public static AccountResponse fromAccount(Account account){
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .currency(account.getCurrency().name())
                .type(account.getType().name())
                .iban(account.getIban())
                .build();
    }
    public static PaymentModel toPaymentModel(TransferModel model){
        return PaymentModel.builder()
                .accountId(model.getAccountId())
                .quantity(model.getQuantity())
                .cardId(model.getCardId())
                .build();
    }
}
