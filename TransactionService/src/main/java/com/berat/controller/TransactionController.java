package com.berat.controller;

import static com.berat.constant.EndPoint.*;

import com.berat.constant.MessageResponse;
import com.berat.dto.request.PaymentTransactionRequest;
import com.berat.dto.request.TransferTransactionRequest;
import com.berat.dto.response.TransactionResponse;
import com.berat.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(CREATE+TRANSFER)
    public ResponseEntity<MessageResponse> createTransferTransaction(@RequestBody TransferTransactionRequest dto){
        transactionService.createTransferTransaction(dto);
        return ResponseEntity.ok(MessageResponse.builder().message("Transaction created successfully!").build());
    }
    @PostMapping(CREATE+PAYMENT)
    public ResponseEntity<MessageResponse> createPaymentTransaction(@RequestBody PaymentTransactionRequest dto){
        transactionService.createPaymentTransaction(dto);
        return ResponseEntity.ok(MessageResponse.builder().message("Transaction created successfully!").build());
    }
    @GetMapping(GETALL+BYACCOUNTID)
    public ResponseEntity<Slice<TransactionResponse>> getAllByAccountId(@PathVariable Long accountId){
        return ResponseEntity.ok(transactionService.getAllByAccountId(accountId));
    }
    @GetMapping(GETONE+BYTRANSACTIONID)
    public ResponseEntity<TransactionResponse> getOneByTransactionId(@PathVariable String transactionId){
        return ResponseEntity.ok(transactionService.getOneByTransactionId(transactionId));
    }

}
