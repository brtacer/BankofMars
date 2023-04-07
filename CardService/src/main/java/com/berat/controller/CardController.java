package com.berat.controller;

import com.berat.constant.MessageResponse;
import com.berat.dto.request.CreateCardRequest;
import com.berat.dto.request.PaymentRequest;
import com.berat.dto.request.UpdateLimitRequest;
import com.berat.dto.response.CardResponse;
import com.berat.model.Card;
import com.berat.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.berat.constant.EndPoint.*;

@RestController
@RequestMapping(CARD)
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping(CREATE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> createCard(@RequestBody CreateCardRequest dto){
        return ResponseEntity.ok(cardService.createCard(dto));
    }
    @PostMapping(PAYMENT)
    public ResponseEntity<MessageResponse> paymentByCard(@RequestBody PaymentRequest dto){
        cardService.paymentByCard(dto);
        return ResponseEntity.ok(MessageResponse.builder().message("Payment made successfully!").build());
    }
    @PutMapping(UPDATE+LIMIT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageResponse> updateCardLimit(@RequestBody UpdateLimitRequest dto){
        cardService.updateCardLimit(dto);
        return ResponseEntity.ok(MessageResponse.builder().message("Card limit updated successfully!").build());
    }
    @GetMapping(GETALL+BYACCOUNTID)
    public ResponseEntity<List<CardResponse>> getAllByAccountId(@PathVariable Long accountId){
        return ResponseEntity.ok(cardService.getAllByAccountId(accountId));
    }
    @GetMapping(GETONE+BYCARDID)
    public ResponseEntity<CardResponse> getOneByCardId(@PathVariable String cardId){
        return ResponseEntity.ok(cardService.getOneByCardId(cardId));
    }
    @PatchMapping(BLOCK+BYCARDID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> blockById(@PathVariable String cardId){
        return ResponseEntity.ok(cardService.blockById(cardId));
    }
    @DeleteMapping(DELETE+BYCARDID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteByCardId(@PathVariable String cardId) {
        return ResponseEntity.ok(cardService.deleteByCardId(cardId));
    }
}
