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
public class CardResponse {
    private String id;
    private BigDecimal boundary;
    private BigDecimal debt;
    private String type;


}
