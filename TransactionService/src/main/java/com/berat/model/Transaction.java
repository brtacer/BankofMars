package com.berat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Transaction extends BaseModel{
    @Id
    private String id;
    private String destinationIbanNumber;
    private String cardId;
    private BigDecimal quantity;
    private Type type;
    private Long accountId;

}
