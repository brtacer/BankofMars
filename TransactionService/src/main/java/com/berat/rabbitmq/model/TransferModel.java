package com.berat.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferModel implements Serializable {
    private Long accountId;
    private BigDecimal quantity;
    private String destinationIbanNumber;
    private String cardId;
}
