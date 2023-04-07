package com.berat.controller;

import com.berat.constant.MessageResponse;
import com.berat.dto.request.AccountCreateRequest;
import com.berat.dto.request.AccountDepositRequest;
import com.berat.dto.request.AccountWithdrawalRequest;
import com.berat.dto.response.AccountResponse;
import com.berat.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.berat.constant.EndPoint.*;

@RestController
@RequestMapping(ACCOUNT)
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping(CREATE)
    public ResponseEntity<MessageResponse> createAccount(@RequestBody AccountCreateRequest dto) {
        accountService.createAccount(dto);
        return ResponseEntity.ok(MessageResponse.builder().message("Account created successfully!").build());
    }
    @PostMapping(DEPOSIT+BYACCOUNTID)
    public ResponseEntity<MessageResponse> depositByAccountId(@RequestBody AccountDepositRequest dto){
        accountService.depositByAccountId(dto);
        return ResponseEntity.ok(MessageResponse.builder().message("Money deposited successfully!").build());
    }
    @PostMapping(WITHDRAWAL+BYACCOUNTID)
    public ResponseEntity<MessageResponse> withdrawalByAccountId(@RequestBody AccountWithdrawalRequest dto){
        accountService.withdrawalByAccountId(dto);
        return ResponseEntity.ok(MessageResponse.builder().message("Money withdrawal made successfully!").build());
    }
    @PatchMapping(DEACTIVATE+BYACCOUNTID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageResponse> deActivateById(@PathVariable Long accountId){
        accountService.deActivateById(accountId);
        return ResponseEntity.ok(MessageResponse.builder().message("Account deactivated successfully!").build());
    }
    @DeleteMapping(DELETE+BYACCOUNTID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageResponse> deleteByAccountId(@PathVariable Long accountId){
        accountService.deleteByAccountId(accountId);
        return ResponseEntity.ok(MessageResponse.builder().message("Account deleted successfully!").build());
    }
    @GetMapping(GETALL+BYCUSTOMERID)
    public ResponseEntity<List<AccountResponse>> getAllByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(accountService.getAllByCustomerId(customerId));
    }
    @GetMapping(GETONE+BYACCOUNTID)
    public ResponseEntity<AccountResponse> getOneAccount(@PathVariable Long accountId){
        return ResponseEntity.ok(accountService.getOneAccount(accountId));
    }
    @GetMapping(EXIST+BYACCOUNTID)
    public ResponseEntity<Boolean> existByAccountId(@PathVariable Long accountId){
        return ResponseEntity.ok(accountService.existByAccountId(accountId));
    }
}
