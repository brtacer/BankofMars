package com.berat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private String id;
    private Date createDate;
    private String destinationIbanNumber;
    private String cardId;
    private BigDecimal quantity;
    private String type;
    private Long accountId;

}
