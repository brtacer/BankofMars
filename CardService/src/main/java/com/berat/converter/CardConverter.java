package com.berat.converter;

import com.berat.dto.request.CreateCardRequest;
import com.berat.dto.response.CardResponse;
import com.berat.model.Card;
import com.berat.model.Type;
import com.berat.util.Generator;

import java.util.Date;

public class CardConverter {

    public static Card toCard(CreateCardRequest dto){
        return Card.builder()
                .accountId(dto.getAccountId())
                .type(Type.valueOf(dto.getType()))
                .CVV(Generator.randomCVV())
                .build();
    }
    public static CardResponse toCardResponse(Card card){
        return CardResponse.builder()
                .id(card.getId())
                .type(card.getType().name())
                .boundary(card.getBoundary())
                .debt(card.getDebt())
                .build();
    }
}
