package com.berat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Card extends BaseModel{
    @Id
    private String id;
    private String CVV;
    @Builder.Default
    private BigDecimal boundary = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal debt = BigDecimal.ZERO;
    private Type type;
    @Builder.Default
    private Status status = Status.ACTIVE;
    private Long accountId;

}
